package spx.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import spx.core.Settings;

public class ContentManager {
    static public void load() {
    }

    public String RootDirectory;
    private Texture _spriteSheet;
    private BitmapFont _font;

    public Texture loadTexture(String resourceName) {
        return AssetManager.get().getImage(resourceName);
    }

    public Sprite loadSprite(int verticalIndex) {
        if (_spriteSheet == null) {
            _spriteSheet = AssetManager.get().getImage("GameplaySheet.png");
        }
        int x = 1;
        int y = verticalIndex * Settings.get().spriteHeight + verticalIndex + 1;
        int width = Settings.get().spriteWidth - 1;
        int height = Settings.get().spriteHeight - 1;
        return new Sprite(_spriteSheet, x, y, width, height);
    }

    public BitmapFont loadFont(String resourceName) {
        if (_font == null) {
            _font = AssetManager.get().getFont("Main.font");
        }
        return _font;
    }
}
