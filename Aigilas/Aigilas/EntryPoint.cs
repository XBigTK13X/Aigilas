using System;

namespace Agilas
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