﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;
using System.Net;
using System.Threading;

namespace SPX.Core
{
    public class Server
    {

        public const int Port = 53216;
        public const string ConnectionName = "Aigilas";
        private static Server __instance;
        
        public static Server Get()
        {
            if (__instance == null)
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
            }
            catch (Exception)
            {
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
                        _announcement.Write(_server.ConnectionsCount);
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
