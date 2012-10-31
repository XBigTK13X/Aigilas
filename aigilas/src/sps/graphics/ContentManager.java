package sps.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.core.Settings;

public class ContentManager {


    private static class Dimensions {
        private static Dimensions instance = new Dimensions();

        public static Dimensions get(int frame, int index) {
            return instance.reset(frame, index);
        }

        private Dimensions() {
        }

        ;

        public int Height;
        public int Width;
        public int X;
        public int Y;

        private Dimensions reset(int frame, int index) {
            X = frame * Settings.get().spriteHeight + (Settings.get().spriteGap * frame) + Settings.get().spriteGap;
            Y = index * Settings.get().spriteHeight + (Settings.get().spriteGap * index) + Settings.get().spriteGap;
            Width = Settings.get().spriteWidth - Settings.get().spriteGap;
            Height = Settings.get().spriteHeight - Settings.get().spriteGap;
            return this;
        }
    }

    private Texture _spriteSheet;
    private BitmapFont _font;

    public Texture loadTexture(String resourceName) {
        return AssetManager.get().getImage(resourceName);
    }

    Dimensions d;

    public Sprite loadSprite(int verticalIndex) {
        if (_spriteSheet == null) {
            _spriteSheet = AssetManager.get().getImage("GameplaySheet.png");
        }
        d = Dimensions.get(0, verticalIndex);
        return new Sprite(_spriteSheet, d.X, d.Y, d.Width, d.Height);
    }

    public void setSpriteIndices(Sprite texture, int frame, int index) {
        d = Dimensions.get(frame, index);
        texture.setRegion(d.X, d.Y, d.Width, d.Height);
    }
}
