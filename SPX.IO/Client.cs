using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;
using SPX.Core;
using SPX.DevTools;

namespace SPX.IO
{
    public enum ClientMessageType:byte
    {
        CONNECT,
        MOVEMENT,
        START_GAME,
        CHECK_STATE,
        PLAYER_COUNT
    }

    public static class CmtString
    {

        public static string Get(ClientMessageType messageType)
        {
            switch(messageType)
            {
                case ClientMessageType.CONNECT:return "CONNECT";
                case ClientMessageType.MOVEMENT: return "MOVEMENT";
                case ClientMessageType.START_GAME: return "START_GAME";
                case ClientMessageType.CHECK_STATE: return "CHECK_STATE";
                case ClientMessageType.PLAYER_COUNT: return "PLAYER_COUNT";
            }
            return "INVALID MESSAGE TYPE";
        }
    }

    public class Client
    {
        private static Client __instance;
        public static Client Get()
        {
            if (__instance == null)
            {
                Init();
            }
            return __instance;
        }

        public static void Init()
        {
            __instance = new Client();
        }

        public static bool IsInUse()
        {
            return true;
        }

        private NetClient _client;
        private NetPeerConfiguration _config;
        private NetIncomingMessage _message;
        private NetOutgoingMessage _outMessage;        
        private Int32 _initialPlayerIndex;
        private bool _isGameStarting;

        private Client()
        {
            _config = new NetPeerConfiguration(Server.ConnectionName);      
            _client = new NetClient(_config);
            _client.Start();
            var init = _client.CreateMessage();
            init.Write((byte)ClientMessageType.CONNECT);      
            _client.Connect("localhost", Server.Port, init);
        }

        //Client<->Game communication
        public bool IsGameStarting()
        {
            return _isGameStarting;
        }

        private Dictionary<int, Dictionary<int, bool>> _playerStatus = new Dictionary<int, Dictionary<int, bool>>();
        private void InitPlayer(int playerIndex, int command)
        {
            if (!_playerStatus.ContainsKey(playerIndex))
            {
                _playerStatus.Add(playerIndex, new Dictionary<int, bool>());
            }
            if (!_playerStatus[playerIndex].ContainsKey(command))
            {
                _playerStatus[playerIndex].Add(command, false);
            }
        }

        public int GetFirstPlayerIndex()
        {
            return _initialPlayerIndex;
        }
        

        //Client<->Server communication
        public bool IsActive(int command, int playerIndex)
        {
            Console.WriteLine("CLIENT: Check PI({0}) CMD({1})", playerIndex, command);
            SendMessage(MessageContents.CreateCheckState(command, playerIndex));
            AwaitReply(ClientMessageType.CHECK_STATE);
            return _contents.IsActive;
        }

        public void SetState(int command, int playerIndex, bool isActive)
        {
            InitPlayer(playerIndex, command);
            if (_playerStatus[playerIndex][command] != isActive)
            {
                Console.WriteLine("CLIENT: Moves: CMD({0}) PI({1}) AC({2})", command, playerIndex, isActive);
                _playerStatus[playerIndex][command] = isActive;
                SendMessage(MessageContents.CreateMovement(command, playerIndex, isActive));
            }
        }

        public int GetPlayerCount()
        {
            SendMessage(MessageContents.CreatePlayerCount(0));
            AwaitReply(ClientMessageType.PLAYER_COUNT);
            return _contents.PlayerCount;
        }

        public void StartGame()
        {
            SendMessage(MessageContents.Create(ClientMessageType.START_GAME));
        }

        private void SendMessage(MessageContents contents)
        {
            _outMessage = _client.CreateMessage();
            _outMessage.Write(contents.ToBytes());
            _client.SendMessage(_outMessage, NetDeliveryMethod.ReliableOrdered);
        }

        //Be careful using this method.
        //If the server doesn't reply at some point with the messageType you expect
        //Then the client will hang in an infinite loop.
        private void AwaitReply(ClientMessageType messageType)
        {
            Server.Get().Update();
            _message = _client.ReadMessage();
            if (_message != null)
            {
                _contents.FromBytes(_message.ReadBytes(MessageContents.ByteCount));
                if (_contents.MessageType == messageType)
                {
                    return;
                }
                else
                {
                    if (_message.MessageType == NetIncomingMessageType.Data)
                    {
                        HandleResponse(_contents);
                    }
                }
            }            
        }

        private void HandleResponse(MessageContents contents)
        {
            switch (contents.MessageType)
            {
                case ClientMessageType.CONNECT:
                    RNG.Seed(contents.RngSeed);
                    _initialPlayerIndex = contents.PlayerCount;
                    break;
                case ClientMessageType.START_GAME:
                    _isGameStarting = true;
                    break;
                default:
                    break;
            }
        }

        private MessageContents _contents = MessageContents.Empty();
        public void Update()
        {
            while ((_message = _client.ReadMessage()) != null && _message.MessageType == NetIncomingMessageType.Data)
            {
                _contents.FromBytes(_message.ReadBytes(MessageContents.ByteCount));
                HandleResponse(_contents);
            }
        }        
    }
}
