package sps.text;

import sps.core.Point2;
import sps.graphics.Renderer;

public class Text {
    protected String _contents;
    protected final Point2 _position = new Point2(0, 0);
    protected TextType _textType = TextType.Inventory;

    public Text() {
    }

    public void reset(String contents, int x, int y) {
        _contents = contents;
        _position.reset(x, y);
    }

    public Text(String contents, int x, int y, TextType type) {
        reset(contents, x, y);
        _textType = type;
    }

    public int update() {
        return 0;
    }

    public TextType getTextType() {
        return _textType;
    }

    protected void DrawText(Renderer target) {
    }

    public void draw() {

    }
}
