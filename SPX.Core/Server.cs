using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;
using System.Net;

namespace SPX.Core
{
    public class Server
    {

        public const int Port = 53216;
        public const string ConnectionName = "Aigilas";
        private static Server __instance;
        
        public static void Setup()
        {
            try
            {
                __instance = new Server();
            }
            catch (Exception)
            {

            }
        }

        public static void Update()
        {
            if (__instance != null)
            {
                __instance.Listen();
            }
        }

        private NetIncomingMessage _message;
        private NetPeerConfiguration _config;
        private NetServer _server;

        private Int32 _rngSeed = Environment.TickCount;

        private Server()
        {
            _config = new NetPeerConfiguration(ConnectionName) { Port = Port };
            _config.MaximumConnections = 200;
            _config.EnableMessageType(NetIncomingMessageType.ConnectionApproval);
            _server = new NetServer(_config);
            _server.Start();
        }        
        
        public void Listen()
        {
            while ((_message = _server.ReadMessage()) != null)
            {
                switch (_message.MessageType)
                {
                    case NetIncomingMessageType.ConnectionApproval:
                        _message.SenderConnection.Approve();
                        Reply(_rngSeed, _message.SenderConnection);
                        break;
                    case NetIncomingMessageType.Data:
                        switch (_message.ReadByte())
                        {
                            case (byte)ClientMessageType.MOVEMENT:
                                Announce(_message.Data);
                                break;
                            case (byte)ClientMessageType.RANDOM_SEED:
                                Reply(_rngSeed, _message.SenderConnection);
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
        public void Announce(byte[] message)
        {
            _announcement = _server.CreateMessage();
            _announcement.Write(message);
            _server.SendMessage(_announcement,_server.Connections,NetDeliveryMethod.ReliableOrdered,0);
        }

        public void Reply(Int32 message, NetConnection target)
        {
            _announcement = _server.CreateMessage();
            _announcement.Write(message);
            _server.SendMessage(_announcement, target, NetDeliveryMethod.ReliableOrdered,0);
        }
    }
}
