using System;
using SPX.Core;
using System.Diagnostics;
using System.Threading;

namespace Aigilas
{
#if WINDOWS || XBOX
    internal static class EntryPoint
    {
        public class ServerThread
        {
            public void DoWork()
            {
                Console.WriteLine("Starting worker thread.");
                Server.Setup();
                while (true)
                {                    
                    Server.Get().Update();
                }
            }
        }

        public class ClientThread
        {
            public void DoWork()
            {
                Console.WriteLine("Starting client thread");
                Client.Init();
                while (true)
                {
                    Client.Get().Update();
                }
            }
        }

        private static void Main(string[] args)
        {
            using (Game game = new Game())
            {
                try
                {
                    Thread serverThread = new Thread(new ServerThread().DoWork);
                    serverThread.Start();                    
                }
                catch (Exception){}
                Thread.Sleep(100);
                try
                {
                    Thread clientThread = new Thread(new ClientThread().DoWork);
                    clientThread.Start();
                }
                catch (Exception){}
                Thread.Sleep(100);
                game.Run();
            }
        }
    }
#endif
}