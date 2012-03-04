using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Core
{
    public static class GameManager
    {
        public static readonly int SpriteHeight = 32;
        public static readonly int SpriteWidth = 32;
        public static readonly int TileMapHeight = 20;
        public static readonly int TileMapWidth = 30;
        public static readonly float SpriteRadius = (float)Math.Sqrt(Math.Pow(SpriteHeight / 2, 2) + Math.Pow(SpriteWidth, 2));
    }
}
