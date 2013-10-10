package sps.graphics;

import sps.bridge.SpriteType;

import java.util.HashMap;
import java.util.List;

public class SpriteSheetManager {
    private static final HashMap<SpriteType, SpriteInfo> __manager = new HashMap<SpriteType, SpriteInfo>();

    public static SpriteInfo getSpriteInfo(SpriteType spriteName) {
        return __manager.get(spriteName);
    }

    public static void setup(List<SpriteDefinition> sprites) {
        for (SpriteDefinition sprite : sprites) {
            __manager.put(sprite.Type, sprite.Info);
        }
    }
}
