package spx.core;

public class GameManager {
	public static float SpriteRadius = (float) Math.sqrt(Math.pow(Settings.Get().spriteHeight / 2, 2) + Math.pow(Settings.Get().spriteWidth, 2));
	public static int AnimationFps = 20;
}
