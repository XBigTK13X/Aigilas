using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Lidgren.Network;

namespace SPX.Core
{
    public class Client
    {
        NetClient _client;
        NetIncomingMessage _message;

        public void Update()
        {
            while ((_message = _client.ReadMessage()) != null)
            {
                Console.WriteLine("CLIENT:"+_message.ReadString());
            }
        }
    }
}
