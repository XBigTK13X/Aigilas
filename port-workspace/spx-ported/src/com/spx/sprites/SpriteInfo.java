package com.spx.sprites;import java.util.*;import com.spx.core.*;import com.xna.wrapper.*;
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
