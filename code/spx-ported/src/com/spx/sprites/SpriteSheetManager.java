package com.spx.sprites;import com.xna.wrapper.*;import java.util.*;
    public class SpriteSheetManager
    {
        private static HashMap<Integer, SpriteInfo> __manager = new HashMap<Integer, SpriteInfo>();

        public static SpriteInfo GetSpriteInfo(int spriteName)
        {
            return __manager.get(spriteName);
        }

        public static void Setup(ISpriteInitializer initializer)
        {
            for(SpriteDefinition sprite:initializer.GetSprites())
            {
                __manager.put(sprite.Type, sprite.Info);
            }
        }
    }
