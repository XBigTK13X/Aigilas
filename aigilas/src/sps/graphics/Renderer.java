package sps.graphics;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.core.Settings;
import sps.core.SpxManager;

import java.io.File;

public class Renderer {

    private final String fontAsset = "assets/graphics/main.fnt";
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final BitmapFont font;

    private static final Color __defaultFilter = Color.WHITE;
    private static final float __defaultAlpha = 1f;

    public Renderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SpxManager.VirtualWidth, SpxManager.VirtualHeight);
        batch = new SpriteBatch();
        File fontFile = new File(fontAsset);
        font = new BitmapFont(Gdx.files.getFileHandle(fontFile.getAbsolutePath(), Files.FileType.Absolute), false);
    }

    public void begin() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    // Sprite rendering
    public void draw(Sprite sprite, Point2 position, DrawDepth depth, Color color) {
        render(sprite, position, depth, color, Settings.get().spriteWidth, Settings.get().spriteHeight);
    }

    public void draw(Sprite sprite, Point2 position, DrawDepth depth, Color color, float scaleX, float scaleY) {
        render(sprite, position, depth, color, scaleX, scaleY);
    }

    private void render(Sprite sprite, Point2 position, DrawDepth depth, Color color, float scaleX, float scaleY) {
        sprite.setColor(color);
        sprite.setSize(scaleX, scaleY);
        sprite.setPosition(position.X, position.Y);
        sprite.draw(batch);
    }

    // String rendering
    public void drawString(String content, Point2 location, Color filter, float scale, DrawDepth depth) {
        renderString(content, location, filter, scale, depth);
    }

    private void renderString(String content, Point2 location, Color filter, float scale, DrawDepth depth) {
        font.setScale(scale);
        font.setColor(filter);
        font.draw(batch, content, location.X, location.Y);
    }
}
