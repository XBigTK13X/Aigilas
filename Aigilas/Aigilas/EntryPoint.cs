using System;
using SPX.Core;
using System.Diagnostics;

namespace Aigilas
{
#if WINDOWS || XBOX
    internal static class EntryPoint
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
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