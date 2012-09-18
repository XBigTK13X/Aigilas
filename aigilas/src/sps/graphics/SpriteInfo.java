package sps.graphics;

import sps.core.Settings;

public class SpriteInfo {
    public final int X;
    public final int Y;
    public final int SpriteIndex;
    public final int MaxFrame;

    public SpriteInfo(int spriteIndex, int maxFrame) {
        X = Settings.get().spriteWidth;
        Y = Settings.get().spriteHeight;
        SpriteIndex = spriteIndex;
        MaxFrame = maxFrame;
    }
}
