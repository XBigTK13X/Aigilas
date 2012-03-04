using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Sprites
{
    public class SpriteInfo
    {
        public static readonly double Radius = (float)Math.Sqrt(Math.Pow(GameManager.SpriteHeight / 2, 2) + Math.Pow(GameManager.SpriteWidth, 2));
        public int X, Y, SpriteIndex, MaxFrame;

        public SpriteInfo(int spriteIndex, int maxFrame)
        {
            X = GameManager.SpriteWidth;
            Y = GameManager.SpriteHeight;
            SpriteIndex = spriteIndex;
            MaxFrame = maxFrame;
        }
    }
}