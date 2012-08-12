package com.spx.sprites;import com.spx.wrapper.*;import java.util.*;import com.spx.core.*;
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
