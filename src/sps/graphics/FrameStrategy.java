package sps.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FrameStrategy implements RenderStrategy {
    private Rectangle viewport;

    @Override
    public OrthographicCamera createCamera() {
        viewport = new Rectangle(0, 0, 0, 0);
        return new OrthographicCamera(Renderer.get().VirtualWidth, Renderer.get().VirtualHeight);
    }

    @Override
    public void begin(OrthographicCamera camera, SpriteBatch batch) {
        camera.setToOrtho(false, Renderer.get().VirtualWidth, Renderer.get().VirtualHeight);
        batch.setProjectionMatrix(camera.combined);
    }

    Vector2 crop = new Vector2(0, 0);

    @Override
    public void resize(int width, int height) {

        float aspectRatio = (float) width / (float) height;
        float scale = 1f;
        crop.set(0, 0);

        if (aspectRatio > Renderer.get().VirtualAspectRatio) {
            scale = (float) height / (float) Renderer.get().VirtualHeight;
            crop.x = (width - Renderer.get().VirtualWidth * scale) / 2f;
        }
        else if (aspectRatio < Renderer.get().VirtualAspectRatio) {
            scale = (float) width / (float) Renderer.get().VirtualWidth;
            crop.y = (height - Renderer.get().VirtualHeight * scale) / 2f;
        }
        else {
            scale = (float) width / (float) Renderer.get().VirtualWidth;
        }

        float w = (float) Renderer.get().VirtualWidth * scale;
        float h = (float) Renderer.get().VirtualHeight * scale;
        viewport.set(crop.x, crop.y, w, h);
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
    }
}
