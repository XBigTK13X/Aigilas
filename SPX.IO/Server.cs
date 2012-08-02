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
        public const bool DEBUG = false;
        public const int Port = 53216;
        public const string ConnectionName = "Aigilas";
        private static Server __instance;
        private static bool __otherServerExists;
        private const int Throttle = 10;

        public static Server Get()
        {
            if (__instance == null && !__otherServerExists)
            {
                __instance = new Server();
            }
            return __instance;
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
                _config.MaximumConnections = 20;
                _config.EnableMessageType(NetIncomingMessageType.ConnectionApproval);
                _server = new NetServer(_config);
                _server.Start();
                for (int ii = 0; ii < MessageContents.PlayerMax; ii++)
                {
                    _playerStatus.Add(ii,new Dictionary<int, bool>());
                    for (int jj = 0; jj < MessageContents.CommandMax; jj++)
                    {
                        _playerStatus[ii].Add(jj,false);
                    }
                }
                __otherServerExists = false;
                Console.WriteLine("Spinning up a server instance");
            }
            catch (Exception)
            {
                __otherServerExists = true;
                Console.WriteLine("SERVER: Failure to start. If this isn't the host, then this message is harmless.");
                DevConsole.Get().Add("SERVER: Failure to start. If this isn't the host, then this message is harmless.");
            }
        }

        private int throttle = 0;
        private const int throttleMax = 100;
        private MessageContents _contents = MessageContents.Empty();
        private bool _firstMessageReceived = false;
        private Int32 _turnCount = 0;
        public virtual void Update()
        {
            while((_message = _server.ReadMessage()) != null)
            {                
                switch (_message.MessageType)
                {
                    case NetIncomingMessageType.ConnectionApproval:
                        Console.WriteLine("SERVER: New client connection");
                        _message.SenderConnection.Approve();
                        Thread.Sleep(100);
                        InitPlayer(_server.ConnectionsCount - 1, 0);
                        Reply(MessageContents.CreateInit(_server.ConnectionsCount - 1, _rngSeed), _message.SenderConnection);
                        if (DEBUG) Console.WriteLine("SERVER: Accepted new connection");
                        _turnCount = 0;
                        Thread.Sleep(100);
                        break;
                    case NetIncomingMessageType.Data:
                        _contents.Deserialize(_message);
                        switch (_contents.MessageType)
                        {
                            case ClientMessageType.CHECK_STATE:
                                InitPlayer(_contents.PlayerIndex, _contents.Command);
                                _contents.IsActive = _playerStatus[_contents.PlayerIndex][_contents.Command];                                
                                if (DEBUG) Console.WriteLine("SERVER: Check: CMD({1}) PI({0}) AC({2})", _contents.PlayerIndex, _contents.Command, _playerStatus[_contents.PlayerIndex][_contents.Command]);
                                Reply(_contents, _message.SenderConnection);
                                break;
                            case ClientMessageType.MOVEMENT:                                
                                InitPlayer(_contents.PlayerIndex, _contents.Command);
                                _playerStatus[_contents.PlayerIndex][_contents.Command] = _contents.IsActive;
                                if (DEBUG) Console.WriteLine("SERVER: Moves: CMD({1}) PI({0}) AC({2})", _contents.PlayerIndex, _contents.Command, _contents.IsActive);
                                break;
                            case ClientMessageType.START_GAME:
                                Console.WriteLine("SERVER: Announcing game commencement.");
                                Announce(_contents);
                                break;
                            case ClientMessageType.PLAYER_COUNT:
                                if (DEBUG) Console.WriteLine("SERVER: PLAYER COUNT");
                                Reply(MessageContents.CreatePlayerCount(_server.ConnectionsCount), _message.SenderConnection);
                                break;
                            default:
                                if (DEBUG) Console.WriteLine("SERVER: Unknown message");
                                break;
                        }
                        break;
                    default:
                        //Console.WriteLine("SERVER: An unhandled MessageType was received: " + _message.ReadString());
                        break;
                }
                _server.Recycle(_message);
            }

            if(DEBUG)Console.WriteLine("SERVER: Announcing player input status.");
            Announce(MessageContents.CreatePlayerState(_playerStatus,_turnCount++));
            Thread.Sleep(Throttle);
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
            if (_server.ConnectionsCount > 0)
            {
                _announcement = _server.CreateMessage(MessageContents.ByteCount);
                contents.Serialize(_announcement);
                _server.SendMessage(_announcement, _server.Connections, NetDeliveryMethod.ReliableOrdered, 0);
            }
        }

        private NetOutgoingMessage _reply;
        private void Reply(MessageContents contents, NetConnection target)
        {
            _reply = _server.CreateMessage(MessageContents.ByteCount);
            contents.Serialize(_reply);
            _server.SendMessage(_reply, target, NetDeliveryMethod.ReliableOrdered, 0);
        }

        public bool IsOnlyInstance()
        {
            return !__otherServerExists;
        }
    }
}
