package sps.graphics;

import sps.bridge.SpriteType;

import java.util.HashMap;

public class SpriteSheetManager {
    private static final HashMap<SpriteType, SpriteInfo> __manager = new HashMap<SpriteType, SpriteInfo>();

    public static SpriteInfo getSpriteInfo(SpriteType spriteName) {
        return __manager.get(spriteName);
    }

    public static void setup(ISpriteInitializer initializer) {
        for (SpriteDefinition sprite : initializer.getSprites()) {
            __manager.put(sprite.Type, sprite.Info);
        }
    }
}
