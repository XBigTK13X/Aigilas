package sps.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StretchStrategy implements RenderStrategy {
    @Override
    public OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Renderer.get().VirtualWidth, Renderer.get().VirtualHeight);
        Gdx.gl.glViewport(0, 0, Renderer.get().getWidth(), Renderer.get().getHeight());
        return camera;
    }

    @Override
    public void begin(OrthographicCamera camera, SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }
}
