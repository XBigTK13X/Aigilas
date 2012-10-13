package sps.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.core.Settings;

public class ContentManager {

    private class Dimensions {
        public final int Height;
        public final int Width;
        public final int X;
        public final int Y;

        public Dimensions(int frame, int index) {
            X = frame * Settings.get().spriteHeight + (Settings.get().spriteGap * frame) + Settings.get().spriteGap;
            Y = index * Settings.get().spriteHeight + (Settings.get().spriteGap * index) + Settings.get().spriteGap;
            Width = Settings.get().spriteWidth - Settings.get().spriteGap;
            Height = Settings.get().spriteHeight - Settings.get().spriteGap;
        }
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
        Dimensions d = new Dimensions(0, verticalIndex);
        return new Sprite(_spriteSheet, d.X, d.Y, d.Width, d.Height);
    }

    public BitmapFont loadFont(String resourceName) {
        if (_font == null) {
            _font = AssetManager.get().getFont("Main.font");
        }
        return _font;
    }

    public void setSpriteIndices(Sprite texture, int frame, int index) {
        Dimensions d = new Dimensions(frame, index);
        texture.setRegion(d.X, d.Y, d.Width, d.Height);
    }
}
