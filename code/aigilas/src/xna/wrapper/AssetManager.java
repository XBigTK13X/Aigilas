package xna.wrapper;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import spx.core.GameManager;

public class AssetManager {
	private static final String assetPath = "assets";
	private static AssetManager instance;
	private static final String __spriteSheetName = "GameplaySheet.png";

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

	public Image GetImage(String fileName) {
		try {
			return new Image(Graphic(fileName));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SpriteSheet GetSpriteSheet() {
		return new SpriteSheet(GetImage(__spriteSheetName),
				GameManager.SpriteWidth, GameManager.SpriteHeight, 1);
	}
}
