package spx.text;

import com.badlogic.gdx.graphics.Color;
import spx.bridge.DrawDepth;
import spx.core.Point2;
import spx.core.Settings;
import spx.core.SpxManager;

class DefaultHudText extends Text {
    private final Color _color;
    private Point2 _origin;

    public DefaultHudText(float alpha) {
        _color = new Color(255f, 255f, 255f, alpha);
    }

    public void Reset(String contents, int x, int y, Point2 origin) {
        _origin = origin;
        Reset(contents, x, y);
    }

    @Override
    public int Update() {
        return 1;
    }

    @Override
    public void Draw() {
        SpxManager.Renderer.DrawString(_contents, new Point2(_position.X + Settings.Get().spriteWidth, SpxManager.WindowHeight - _position.Y + Settings.Get().spriteHeight / 2), _color, 1f, DrawDepth.DefaultHudText);
    }
}
