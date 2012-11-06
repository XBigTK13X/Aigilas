package sps.devtools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.graphics.Assets;
import sps.graphics.Renderer;
import sps.text.TextType;

public class DevConsole {

    public static final int margin = 50;

    private class ConsoleText extends sps.text.Text {
        public ConsoleText(int x, int y, String content) {
            super(content, x, y, TextType.Inventory);
        }

        @Override
        public void draw() {
            Renderer.get().drawString(_contents, _position, Color.WHITE, 1.0f, DrawDepth.DevConsoleText);
        }

        public String getContent() {
            return _contents;
        }
    }

    private static DevConsole __instance;

    public static DevConsole get() {
        if (__instance == null) {
            __instance = new DevConsole();
        }
        return __instance;
    }

    private final int messageLimit = 20;
    private final ConsoleText[] _contents = new ConsoleText[messageLimit];
    private int _index = 0;
    private boolean _isVisible = false;
    private final Color _bgColor;
    private final Sprite _consoleBase;

    private DevConsole() {
        _bgColor = Color.BLACK;
        _bgColor.a = (byte) 180;
        _consoleBase = Assets.get().baseMenu();
        add("The development console has been started.");
    }

    private int getY(int index) {
        return Renderer.VirtualHeight - (index * margin / 2);
    }

    public void add(String message) {
        if (_index < _contents.length) {
            _contents[_index++] = new ConsoleText(margin, getY(_index), message);
        }
        else {
            for (int ii = 0; ii < _contents.length - 1; ii++) {
                _contents[ii] = new ConsoleText(margin, getY(ii), _contents[ii + 1].getContent());
            }
            _contents[_contents.length - 1] = new ConsoleText(margin, getY(_contents.length - 1), message);
        }
    }

    public void draw() {
        if (_isVisible) {
            Renderer.get().draw(_consoleBase, Point2.Zero, DrawDepth.DevConsole, _bgColor, Renderer.VirtualWidth, Renderer.VirtualHeight);
            for (ConsoleText _content : _contents) {
                if (_content != null) {
                    _content.draw();
                }
            }
        }
    }

    public void toggle() {
        _isVisible = !_isVisible;
    }
}
