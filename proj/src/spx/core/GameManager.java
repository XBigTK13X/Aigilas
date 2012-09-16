package spx.core;

public class GameManager {
    public static float SpriteRadius = (float) Math.sqrt(Math.pow(Settings.get().spriteHeight / 2, 2) + Math.pow(Settings.get().spriteWidth, 2));
    public static int AnimationFps = 20;
}
