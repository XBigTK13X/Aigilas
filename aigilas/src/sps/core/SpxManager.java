package sps.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.GdxNativesLoader;
import sps.graphics.ContentManager;
import sps.graphics.Renderer;
import sps.net.Client;

public class SpxManager {
    // This is the resolution used by the game internally
    public static final int WindowHeight = Settings.get().spriteHeight * Settings.get().tileMapHeight;
    public static final int WindowWidth = Settings.get().spriteWidth * Settings.get().tileMapWidth;

    // This is the resolution used to draw on the screen
    // 720
    // 1050
    public static final int RenderHeight = WindowHeight;
    // 1280
    // 1680
    public static final int RenderWidth = WindowWidth;
    private static ContentManager __assetHandler;
    public static Renderer Renderer;

    private static final String __menuBaseSprite = "MenuBase.png";
    private static final String __gameOverSprite = "GameOver.png";
    private static final String __particleSprite = "Particle.png";

    public static void setContentManager(ContentManager assetHandler) {
        __assetHandler = assetHandler;
    }

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
        return new Point2(WindowWidth / 2, WindowHeight / 2);
    }

    public static Point2 getDimensions() {
        return new Point2(WindowWidth, WindowHeight);
    }

    public static void setup() {
        Client.get();
        GdxNativesLoader.load();
        setupCamera(false);
        setContentManager(new ContentManager());
        Renderer = new Renderer();
    }
}
