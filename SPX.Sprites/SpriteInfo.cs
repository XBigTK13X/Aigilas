using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Sprites
{
    public class SpriteInfo
    {
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