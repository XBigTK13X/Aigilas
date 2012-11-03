package sps.text;

import com.badlogic.gdx.graphics.Color;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.graphics.Renderer;

public class StaticText {
    private Point2 position;
    private String message;
    private float scale;
    private boolean visible = false;

    public StaticText() {
    }

    public void reset(Point2 position, String message, float scale) {
        this.position = position;
        this.message = message;
        this.scale = scale;
        visible = true;
    }

    public void hide() {
        visible = false;
    }

    public void draw() {
        Renderer.get().drawString(message, position, Color.WHITE, scale, DrawDepth.ActionText);
    }

    public boolean isVisible() {
        return visible;
    }
}
