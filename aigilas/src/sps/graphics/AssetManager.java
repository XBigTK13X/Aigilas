package sps.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.core.Settings;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {
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

    private static final String assetPath = "assets";
    private static AssetManager instance;
    private static final String __menuBaseSprite = "MenuBase.png";
    private static final String __particleSprite = "Particle.png";
    private static Map<String, Texture> textures = new HashMap<String, Texture>();

    public static AssetManager get() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private static String graphic(String fileName) {
        return assetPath + "/graphics/" + fileName;
    }

    private Texture _spriteSheet;
    private BitmapFont _font;
    Dimensions d;

    private AssetManager() {
    }

    public Texture getImage(String fileName) {
        if (!textures.containsKey(fileName)) {
            textures.put(fileName, new Texture(graphic(fileName)));
        }
        return textures.get(fileName);
    }

    public Sprite getSprite(int verticalIndex) {
        if (_spriteSheet == null) {
            _spriteSheet = getImage("GameplaySheet.png");
        }
        d = Dimensions.get(0, verticalIndex);
        return new Sprite(_spriteSheet, d.X, d.Y, d.Width, d.Height);
    }

    public void setIndices(Sprite texture, int frame, int index) {
        d = Dimensions.get(frame, index);
        texture.setRegion(d.X, d.Y, d.Width, d.Height);
    }

    public Sprite getParticleAsset() {
        return new Sprite(getImage(__particleSprite));
    }

    public Sprite getMenuBaseAsset() {
        return new Sprite(getImage(__menuBaseSprite));
    }
}
