package sps.text;

import com.badlogic.gdx.graphics.Color;
import sps.bridge.DrawDepth;
import sps.core.SpxManager;

public class ActionText extends Text {

    protected int _lifeSpan = 0;
    protected float _scalePercent = 1;

    public ActionText(String contents, int lifeSpan, int x, int y) {
        super(contents, x, y, TextType.Action);
        _lifeSpan = lifeSpan;
    }

    public ActionText() {

    }

    public void reset(String contents, int lifespan, int x, int y) {
        _scalePercent = 1;
        _lifeSpan = lifespan;
        reset(contents, x, y);
    }

    @Override
    public int update() {
        _scalePercent *= .98f;
        return _lifeSpan--;
    }

    @Override
    public void draw() {
        SpxManager.Renderer.drawString(_contents, _position, Color.BLACK, 1.15f * _scalePercent, DrawDepth.ActionTextBG);
        SpxManager.Renderer.drawString(_contents, _position, Color.WHITE, 1.0f * _scalePercent, DrawDepth.ActionText);
    }
}
