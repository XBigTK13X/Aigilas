using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Sprites
{
    public class SpriteInfo
    {
        public const int Height = 32;
        public const int Width = 32;
        public static readonly double Radius = (float)Math.Sqrt(Math.Pow(Height/2, 2) + Math.Pow(Width, 2));
        public int X, Y, SpriteIndex, MaxFrame;

        public SpriteInfo(int spriteIndex, int maxFrame)
        {
            X = Width;
            Y = Height;
            SpriteIndex = spriteIndex;
            MaxFrame = maxFrame;
        }
    }
}