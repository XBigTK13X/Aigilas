package spx.graphics;

import aigilas.management.SpriteType;

import java.util.HashMap;

public class SpriteSheetManager {
    private static HashMap<SpriteType, SpriteInfo> __manager = new HashMap<>();

    public static SpriteInfo GetSpriteInfo(SpriteType spriteName) {
        return __manager.get(spriteName);
    }

    public static void Setup(ISpriteInitializer initializer) {
        for (SpriteDefinition sprite : initializer.GetSprites()) {
            __manager.put(sprite.Type, sprite.Info);
        }
    }
}
