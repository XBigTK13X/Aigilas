package sps.graphics;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.core.Settings;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Assets {
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

    private enum Sprites {
        Gameplay,
        Particle,
        MenuBase
    }

    private static final String assetPath = "assets";
    private static Assets instance;
    private static final String __menuBaseSprite = "MenuBase.png";
    private static final String __particleSprite = "Particle.png";
    private static final String fontAsset = "assets/graphics/main.fnt";

    public static Assets get() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }


    private final BitmapFont _font;
    private final Texture _spriteSheet;
    private final Sprite _sprite;
    Dimensions d;

    private Map<String, Texture> textures = new HashMap<String, Texture>();
    private Map<Sprites, Sprite> sprites = new HashMap<Sprites, Sprite>();

    private Assets() {
        File fontFile = new File(fontAsset);
        _font = new BitmapFont(Gdx.files.getFileHandle(fontFile.getAbsolutePath(), Files.FileType.Absolute), false);
        _spriteSheet = image("GameplaySheet.png");
        d = Dimensions.get(0, 0);
        _sprite = new Sprite(_spriteSheet, d.X, d.Y, d.Width, d.Height);
        sprites.put(Sprites.Particle, new Sprite(image(__particleSprite)));
        sprites.put(Sprites.MenuBase, new Sprite(image(__menuBaseSprite)));
    }

    public BitmapFont font() {
        return _font;
    }

    public Texture image(String fileName) {
        if (!textures.containsKey(fileName)) {
            textures.put(fileName, new Texture(assetPath + "/graphics/" + fileName));
        }
        return textures.get(fileName);
    }

    public Sprite sprite(int verticalIndex) {
        setIndices(_sprite, 0, verticalIndex);
        return _sprite;
    }

    public void setIndices(Sprite sprite, int frame, int index) {
        d = Dimensions.get(frame, index);
        sprite.setRegion(d.X, d.Y, d.Width, d.Height);
    }

    public Sprite particle() {
        return sprites.get(Sprites.Particle);
    }

    public Sprite baseMenu() {
        return sprites.get(Sprites.MenuBase);
    }
}
