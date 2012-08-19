package xna.wrapper;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AssetManager {
	private static final String assetPath = "assets";
	private static AssetManager instance;

	public static AssetManager Get() {
		if (instance == null) {
			instance = new AssetManager();
		}
		return instance;
	}

	private SpriteSheet gameplaySheet;

	private static String Graphic(String fileName) {
		return assetPath + "/graphics/" + fileName;
	}

	private AssetManager() {
		try {
			gameplaySheet = new SpriteSheet(Graphic("GameplaySheet.png"), 32,
					32, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Image GetSprite(String fileName) {
		try {
			return new Image(Graphic(fileName));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
}
