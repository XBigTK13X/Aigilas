using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;

namespace SPX.Core
{
    public class Server
    {

        private NetIncomingMessage _message;
        private NetPeerConfiguration _config;
        private NetServer _server;

        private static Server __instance = new Server();

        private Server()
        {
            _config = new NetPeerConfiguration("Aigilas") { Port = 53216 };
            _server = new NetServer(_config);
            
        }

        public void Update()
        {
            while ((_message = _server.ReadMessage()) != null)
            {
                Console.WriteLine(_message.ReadString());
            }
        }

        public void Start()
        {
            _server.Start();
        }

        public static void Setup()
        {
            __instance.Start();
        }

        public static void Update()
        {
            __instance.Update();
        }
    }
}
