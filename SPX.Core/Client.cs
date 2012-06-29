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
        public static bool IsCommandActive(int command, int playerIndex)
        {
            if (__instance == null)
            {
                return false;
            }
            return __instance.IsActive(command, playerIndex);
        }

        public static void Init()
        {
            __instance = new Client();
        }

        public static bool IsInUse()
        {
            return true;
        }

        public static int GetRngSeed()
        {
            return __instance.GetRngSeedValue();
        }

        public static int GetConnectionCount()
        {
            return __instance.GetPlayerCount();
        }

        public static void SetState(int command, int playerIndex, bool isActive)
        {
            if (__instance != null)
            {
                __instance.ChangeState(command, playerIndex, isActive);
            }
        }

        public static void Update()
        {
            __instance.Discuss();
        }


        private NetClient _client;
        private NetPeerConfiguration _config;
        private NetIncomingMessage _message;
        private NetOutgoingMessage _outMessage;
        private Dictionary<int, Dictionary<int, bool>> _playerStatus = new Dictionary<int, Dictionary<int, bool>>();
        private int _rngSeed;

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
                return _playerStatus[playerIndex][command];
            }
            return false;
        }

        public int GetPlayerCount()
        {
            return _playerStatus.Keys.Count();
        }

        public int GetRngSeedValue()
        {
            return _rngSeed;
        }

        public void Discuss()
        {
            while ((_message = _client.ReadMessage()) != null)
            {
                switch (_message.MessageType)
                {
                    case NetIncomingMessageType.Data:
                        switch (_message.ReadByte())
                        {
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

        public void ChangeState(int command, int playerIndex, bool isActive)
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
                _outMessage = _client.CreateMessage();
                _outMessage.Write((byte)ClientMessageType.MOVEMENT);
                _outMessage.Write((byte)command);
                _outMessage.Write((byte)playerIndex);
                _outMessage.Write(isActive);
                _client.SendMessage(_outMessage, NetDeliveryMethod.ReliableOrdered);
            }
        }
    }
}
