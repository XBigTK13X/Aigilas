using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;
using SPX.Core;
using SPX.DevTools;

namespace SPX.IO
{
    public enum ClientMessageType
    {
        CONNECT,
        MOVEMENT,
        START_GAME
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
        private Dictionary<int, Dictionary<int, bool>> _playerStatus = new Dictionary<int, Dictionary<int, bool>>();
        private Int32 _rngSeed;
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

        public void StartGame()
        {
            _outMessage = _client.CreateMessage();
            _outMessage.Write(new MessageContents(ClientMessageType.START_GAME).ToBytes());
            _client.SendMessage(_outMessage, NetDeliveryMethod.ReliableOrdered);
        }

        public bool IsGameStarting()
        {
            return _isGameStarting;
        }

        public bool IsActive(int command, int playerIndex)
        {
            if (_playerStatus.ContainsKey(playerIndex) && _playerStatus[playerIndex].ContainsKey(command))
            {
                return _playerStatus[playerIndex][command];
            }
            return false;
        }

        public int GetFirstPlayerIndex()
        {
            return _initialPlayerIndex;
        }

        public int GetPlayerCount()
        {
            return _playerStatus.Keys.Count();
        }

        public int GetRngSeed()
        {
            return _rngSeed;
        }

        public bool StateHasChanged(int command, int playerIndex, bool isActive)
        {
            bool stateHasChanged = false;
            if (!_playerStatus.ContainsKey(playerIndex))
            {
                stateHasChanged = true;
            }
            else
            {
                if (!_playerStatus[playerIndex].ContainsKey(command))
                {
                    stateHasChanged = true;
                }
                else
                {
                    if (_playerStatus[playerIndex][command] != isActive)
                    {
                        stateHasChanged = true;
                    }
                }
            }
            return stateHasChanged;
        }

        public void SetState(int command, int playerIndex, bool isActive)
        {
            _outMessage = _client.CreateMessage();
            _outMessage.Write(new MessageContents(ClientMessageType.MOVEMENT,playerIndex,command,isActive).ToBytes());
            _client.SendMessage(_outMessage, NetDeliveryMethod.ReliableOrdered);
        }

        private MessageContents _contents = new MessageContents();
        public void Update()
        {
            while ((_message = _client.ReadMessage()) != null && _message.MessageType == NetIncomingMessageType.Data)
            {
                _contents.FromBytes(_message.ReadBytes(MessageContents.ByteCount));
                switch (_contents.MessageType)
                {
                    case ClientMessageType.CONNECT:
                        RNG.Seed(_contents.RngSeed);
                        _initialPlayerIndex = _contents.PlayerCount;
                        break;
                    case ClientMessageType.MOVEMENT:
                        if (!_playerStatus.ContainsKey(_contents.PlayerIndex))
                        {
                            _playerStatus.Add(_contents.PlayerIndex, new Dictionary<int, bool>());
                        }
                        if (!_playerStatus[_contents.PlayerIndex].ContainsKey(_contents.Command))
                        {
                            _playerStatus[_contents.PlayerIndex].Add(_contents.Command, _contents.IsActive);
                        }
                        _playerStatus[_contents.PlayerIndex][_contents.Command] = _contents.IsActive;
                        break;
                    case ClientMessageType.START_GAME:
                        _isGameStarting = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
