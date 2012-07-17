using System;
using SPX.Core;
using System.Diagnostics;
using System.Threading;
using SPX.IO;

namespace Aigilas
{
    internal static class EntryPoint
    {
        private static class ServerThread
        {
            public static void Work()
            {
                Server.Get();
                Thread.Sleep(50);
                while (Server.Get().IsOnlyInstance())
                {
                    Server.Get().Update();
                }
                Console.WriteLine("Finished being a server");
            }
        }
        private static void Main(string[] args)
        {
            Thread server = new Thread(new ThreadStart(ServerThread.Work));
            server.Start();
            Thread.Sleep(100);
            using (Game game = new Game())
            {
                game.Run();
            }
            Console.WriteLine("Finished being a client");
        }
    }
}