using System;
using SPX.Core;
using System.Diagnostics;
using System.Threading;
using Aigilas.IO;

namespace Aigilas
{
#if WINDOWS || XBOX
    internal static class EntryPoint
    {
        private static void Main(string[] args)
        {
            using (Game game = new Game())
            {
                game.Run();
            }
        }
    }
#endif
}