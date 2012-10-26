package sps.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.GdxNativesLoader;
import sps.graphics.ContentManager;
import sps.graphics.Renderer;
import sps.net.Client;

public class Spx {
    // This is the resolution used by the game internally
    public static final int VirtualHeight = Settings.get().spriteHeight * Settings.get().tileMapHeight;
    public static final int VirtualWidth = Settings.get().spriteWidth * Settings.get().tileMapWidth;

    private static ContentManager __assetHandler;
    public static Renderer Renderer;

    private static final String __menuBaseSprite = "MenuBase.png";
    private static final String __gameOverSprite = "GameOver.png";
    private static final String __particleSprite = "Particle.png";

    private static Texture getAsset(String resourceName) {
        return __assetHandler.loadTexture(resourceName);
    }

    public static Sprite getParticleAsset() {
        return new Sprite(getAsset(__particleSprite));
    }

    public static Sprite getMenuBaseAsset() {
        return new Sprite(getAsset(__menuBaseSprite));
    }

    public static Sprite getSpriteAsset(int index) {
        return __assetHandler.loadSprite(index);
    }

    public static void setSpriteIndices(Sprite texture, int frame, int index) {
        __assetHandler.setSpriteIndices(texture, frame, index);
    }

    public static Texture getGameOverAsset() {
        return getAsset(__gameOverSprite);
    }

    public static void setupCamera(boolean isFullScreen) {

        Renderer = new Renderer();
    }

    public static Point2 getCenter() {
        return new Point2(VirtualWidth / 2, VirtualHeight / 2);
    }

    public static Point2 getDimensions() {
        return new Point2(VirtualWidth, VirtualHeight);
    }

    public static void setup() {
        Client.get();
        GdxNativesLoader.load();
        setupCamera(false);
        __assetHandler = new ContentManager();
        Renderer = new Renderer();
    }
}
