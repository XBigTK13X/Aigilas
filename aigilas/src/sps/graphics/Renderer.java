package sps.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.core.Settings;
import sps.core.SpxManager;

public class Renderer {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final BitmapFont font;

    private static final Color __defaultFilter = Color.WHITE;
    private static final float __defaultAlpha = 1f;

    public Renderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SpxManager.WindowWidth, SpxManager.WindowHeight);
        batch = new SpriteBatch();
        font = new BitmapFont();
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

    // Texture rendering
    public void draw(Texture image, Point2 location, DrawDepth depth, Color filter) {
        render(image, location, filter, depth, __defaultAlpha);
    }

    public void draw(Texture image, Point2 location, DrawDepth depth, float alpha) {
        render(image, location, __defaultFilter, depth, alpha);
    }

    public void draw(Texture image, Point2 location, DrawDepth depth) {
        render(image, location, __defaultFilter, depth, __defaultAlpha);
    }

    public void draw(Texture image, Point2 location) {
        render(image, location, __defaultFilter, DrawDepth.Default, __defaultAlpha);
    }

    public void draw(Texture texture, Point2 position, DrawDepth depth, Color filter, int scaleX, int scaleY) {
        batch.draw(texture, position.PosX, position.PosY, 0, 0, scaleX, scaleY);
    }

    private void render(Texture image, Point2 location, Color filter, DrawDepth depth, float alpha) {
        batch.setColor(filter);
        batch.draw(image, location.PosX, location.PosY);
        batch.setColor(0, 0, 0, 1);
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
