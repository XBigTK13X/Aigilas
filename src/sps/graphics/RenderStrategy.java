package sps.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderStrategy {
    public OrthographicCamera createCamera();

    public void begin(OrthographicCamera camera, SpriteBatch batch);

    public void resize(int width, int height);
}
