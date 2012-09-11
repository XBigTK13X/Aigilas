package spx.text;

import spx.core.Point2;
import spx.graphics.Renderer;

public class Text {
	protected String _contents;
	protected Point2 _position = new Point2(0, 0);
	protected TextType _textType = TextType.Inventory;

	public Text() {
	}

	public void Reset(String contents, int x, int y) {
		_contents = contents;
		_position.Reset(x, y);
	}

	public Text(String contents, int x, int y, TextType type) {
		Reset(contents, x, y);
		_textType = type;
	}

	public int Update() {
		return 0;
	}

	public TextType GetTextType() {
		return _textType;
	}

	protected void DrawText(Renderer target) {
	}

	public void Draw() {

	}
}
