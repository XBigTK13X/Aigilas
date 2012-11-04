package sps.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.graphics.Renderer;

public class StaticText {
    public static final float NotTimed = Float.MIN_VALUE;

    private Point2 position;
    private String message;
    private float scale;
    private boolean visible = false;
    private float lifeInSeconds;


    public StaticText() {
    }

    public void reset(Point2 position, String message, float scale, float lifeInSeconds) {
        this.position = position;
        this.message = message;
        this.scale = scale;
        visible = true;
        this.lifeInSeconds = lifeInSeconds;
    }

    public void hide() {
        visible = false;
    }

    public void update() {
        if (lifeInSeconds != NotTimed) {
            lifeInSeconds -= Gdx.graphics.getDeltaTime();
            if (lifeInSeconds <= 0) {
                visible = false;
            }
        }
    }

    public void draw() {
        Renderer.get().drawString(message, position, Color.WHITE, scale, DrawDepth.ActionText);
    }

    public boolean isVisible() {
        return visible;
    }
}
