using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;
using System.Net;
using System.Threading;
using SPX.DevTools;

namespace SPX.IO
{
    public class Server
    {

        public const int Port = 53216;
        public const string ConnectionName = "Aigilas";
        private static Server __instance;
        private static bool __otherServerExists;

        public static Server Get()
        {
            if (__instance == null && !__otherServerExists)
            {
                Setup();
            }
            return __instance;
        }

        public static void Setup()
        {
            __instance = new Server();
        }

        private NetIncomingMessage _message;
        private NetPeerConfiguration _config;
        private NetServer _server;

        private Dictionary<int, Dictionary<int, bool>> _playerStatus = new Dictionary<int, Dictionary<int, bool>>();
        private int _rngSeed = Environment.TickCount;

        private Server()
        {
            try
            {
                _config = new NetPeerConfiguration(ConnectionName) { Port = Port };
                _config.MaximumConnections = 200;
                _config.EnableMessageType(NetIncomingMessageType.ConnectionApproval);
                _server = new NetServer(_config);
                _server.Start();
                __otherServerExists = false;
            }
            catch (Exception)
            {
                DevConsole.Get().Add("SERVER: Failure to start. If this isn't the host, then this message is harmless.");
            }
        }

        private MessageContents _contents = MessageContents.Empty();
        public void Update()
        {
            while ((_message = _server.ReadMessage()) != null)
            {
                switch (_message.MessageType)
                {
                    case NetIncomingMessageType.ConnectionApproval:
                        _message.SenderConnection.Approve();                        
                        Thread.Sleep(100);
                        InitPlayer(_server.ConnectionsCount - 1, 0);
                        Reply(MessageContents.CreateInit(_server.ConnectionsCount - 1, _rngSeed),_message.SenderConnection);
                        break;
                    case NetIncomingMessageType.Data:
                        _contents.FromBytes(_message.ReadBytes(MessageContents.ByteCount));
                         switch (_contents.MessageType)
                            {
                                case ClientMessageType.CHECK_STATE:
                                    InitPlayer(_contents.PlayerIndex, _contents.Command);
                                    _contents.IsActive = _playerStatus[_contents.PlayerIndex][_contents.Command];
                                    //Console.WriteLine("SERVER: Check: CMD({1}) PI({0}) AC({2})", _contents.PlayerIndex, _contents.Command, _playerStatus[_contents.PlayerIndex][_contents.Command]);
                                    Reply(_contents,_message.SenderConnection);                                    
                                    break;
                                case ClientMessageType.MOVEMENT:
                                    InitPlayer(_contents.PlayerIndex,_contents.Command);
                                    _playerStatus[_contents.PlayerIndex][_contents.Command] = _contents.IsActive;
                                    //Console.WriteLine("SERVER: Moves: CMD({1}) PI({0}) AC({2})", _contents.PlayerIndex, _contents.Command, _contents.IsActive);
                                    break;
                                case ClientMessageType.START_GAME:
                                    Announce(_contents);
                                    break;
                                case ClientMessageType.PLAYER_COUNT:
                                    Reply(MessageContents.CreatePlayerCount(_playerStatus.Keys.Count),_message.SenderConnection);
                                    break;
                                default:
                                    break;
                            }
                            break;
                    default:
                        //Console.WriteLine("SERVER: An unhandled MessageType was received: " + _message.ReadString());
                        break;
                }
            }
        }

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

        private NetOutgoingMessage _announcement;
        private void Announce(MessageContents contents)
        {
            _announcement = _server.CreateMessage();
            _announcement.Write(contents.ToBytes());
            _server.SendMessage(_announcement,_server.Connections,NetDeliveryMethod.ReliableOrdered,0);
        }

        private NetOutgoingMessage _reply;
        private void Reply(MessageContents contents, NetConnection target)
        {
            _reply = _server.CreateMessage();
            _reply.Write(contents.ToBytes());
            _server.SendMessage(_reply, target, NetDeliveryMethod.ReliableOrdered, 0);
        }
    }
}
