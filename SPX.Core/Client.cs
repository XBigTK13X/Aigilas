using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;

namespace SPX.Core
{
    public enum ClientMessageType
    {
        CONNECT,
        MOVEMENT,
        RANDOM_SEED
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

        private Client()
        {

            _config = new NetPeerConfiguration(Server.ConnectionName);      
            _client = new NetClient(_config);
            _client.Start();
            var init = _client.CreateMessage();
            init.Write((byte)ClientMessageType.CONNECT);      
            _client.Connect("localhost", Server.Port, init);
        }


        public bool IsActive(int command, int playerIndex)
        {
            if (_playerStatus.ContainsKey(playerIndex) && _playerStatus[playerIndex].ContainsKey(command))
            {
                Console.WriteLine(_playerStatus[playerIndex][command]);
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


        public void SetState(int command, int playerIndex, bool isActive)
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
            if (stateHasChanged)
            {
                Console.WriteLine("StateChange");
                _outMessage = _client.CreateMessage();
                _outMessage.Write((byte)ClientMessageType.MOVEMENT);
                _outMessage.Write((byte)command);
                _outMessage.Write((byte)playerIndex);
                _outMessage.Write(isActive);
                _client.SendMessage(_outMessage, NetDeliveryMethod.ReliableOrdered);
            }
        }

        public void Update()
        {
            while ((_message = _client.ReadMessage()) != null)
            {
                switch (_message.MessageType)
                {
                    case NetIncomingMessageType.Data:
                        switch (_message.ReadByte())
                        {
                            case (byte)ClientMessageType.CONNECT:
                                Int32 seed = _message.ReadInt32();
                                Int32 connectionCount = _message.ReadInt32();
                                RNG.Seed(seed);
                                _initialPlayerIndex = connectionCount;
                                break;
                            case (byte)ClientMessageType.MOVEMENT:
                                int command = _message.ReadByte();
                                int playerIndex = _message.ReadByte();
                                bool isActive = _message.ReadBoolean();
                                if (!_playerStatus.ContainsKey(playerIndex))
                                {
                                    _playerStatus.Add(playerIndex, new Dictionary<int, bool>());
                                }
                                if (!_playerStatus[playerIndex].ContainsKey(command))
                                {
                                    _playerStatus[playerIndex].Add(command, isActive);
                                }
                                _playerStatus[playerIndex][command] = isActive;
                                break;
                            case (byte)ClientMessageType.RANDOM_SEED:
                                _rngSeed = _message.ReadInt32();
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
