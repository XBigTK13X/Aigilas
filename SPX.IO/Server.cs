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
        private Int32 _rngSeed = Environment.TickCount;

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

        private MessageContents _contents = new MessageContents();
        public void Update()
        {
            while ((_message = _server.ReadMessage()) != null)
            {
                switch (_message.MessageType)
                {
                    case NetIncomingMessageType.ConnectionApproval:
                        Console.WriteLine("SERVER: New Connection {"+_server.ConnectionsCount+" total}");
                        _message.SenderConnection.Approve();
                        Thread.Sleep(100);
                        Announce(new MessageContents(ClientMessageType.CONNECT, _server.ConnectionsCount - 1, _rngSeed));
                        break;
                    case NetIncomingMessageType.Data:
                        _contents.FromBytes(_message.ReadBytes(MessageContents.ByteCount));
                        switch (_contents.MessageType)
                            {
                                case ClientMessageType.MOVEMENT:
                                case ClientMessageType.START_GAME:
                                    Announce(_contents);
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

        private NetOutgoingMessage _announcement;
        private void Announce(MessageContents contents)
        {
            _announcement = _server.CreateMessage();
            _announcement.Write(contents.ToBytes());
            _server.SendMessage(_announcement,_server.Connections,NetDeliveryMethod.ReliableOrdered,0);
        }
    }
}
