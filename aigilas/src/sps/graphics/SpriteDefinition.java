package sps.graphics;

import sps.bridge.SpriteType;

public class SpriteDefinition {
    public final SpriteType Type;
    public final SpriteInfo Info;

    public SpriteDefinition(SpriteType type, int index, int frames) {
        Type = type;
        Info = new SpriteInfo(index, frames);
    }
}
