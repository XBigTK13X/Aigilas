using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;
using System.Net;
using System.Threading;
using SPX.IO;
using SPX.DevTools;

namespace Aigilas.IO
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
                        _announcement = _server.CreateMessage();
                        _announcement.Write((byte)ClientMessageType.CONNECT);
                        _announcement.Write(_rngSeed);
                        _announcement.Write(_server.ConnectionsCount-1);
                        _server.SendMessage(_announcement, _message.SenderConnection, NetDeliveryMethod.ReliableOrdered,0);                        
                        break;
                    case NetIncomingMessageType.Data:
                        switch (_message.ReadByte())
                        {
                            case (byte)ClientMessageType.MOVEMENT:
                                Announce(_message.Data);
                                break;
                            case (byte)ClientMessageType.RANDOM_SEED:
                                break;
                            case (byte)ClientMessageType.START_GAME:
                                Announce(_message.Data);
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
        private void Announce(byte[] message)
        {
            _announcement = _server.CreateMessage();
            _announcement.Write(message);
            _server.SendMessage(_announcement,_server.Connections,NetDeliveryMethod.ReliableOrdered,0);
        }
    }
}
