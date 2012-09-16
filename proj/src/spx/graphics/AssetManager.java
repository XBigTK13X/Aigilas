package spx.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetManager {
    private static final String assetPath = "assets";
    private static AssetManager instance;

    public static AssetManager get() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private static String graphic(String fileName) {
        return assetPath + "/graphics/" + fileName;
    }

    private AssetManager() {
    }

    public Texture getImage(String fileName) {
        return new Texture(graphic(fileName));
    }

    public BitmapFont getFont(String string) {
        return new BitmapFont();
    }
}
