package sps.graphics;

import sps.core.SpsConfig;

public class SpriteInfo {
    public final int X;
    public final int Y;
    public final int SpriteIndex;
    public final int MaxFrame;

    public SpriteInfo(int spriteIndex, int maxFrame) {
        X = SpsConfig.get().spriteWidth;
        Y = SpsConfig.get().spriteHeight;
        SpriteIndex = spriteIndex;
        MaxFrame = maxFrame;
    }
}
