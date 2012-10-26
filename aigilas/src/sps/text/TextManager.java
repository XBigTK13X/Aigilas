package sps.text;

import sps.core.Spx;

import java.util.ArrayList;
import java.util.List;

public class TextManager {
    static private final List<Text> _contents = new ArrayList<Text>();

    public static void add(Text textToAdd) {
        if (!_contents.contains(textToAdd)) {
            _contents.add(textToAdd);
        }
    }

    public static void clear() {
        _contents.clear();
    }

    public static void update() {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).update() <= 0) {
                _contents.remove(_contents.get(ii));
                ii--;
            }
        }
    }

    public static void draw() {
        for (Text component : _contents) {
            if (Spx.Renderer != null) {
                component.draw();
            }
        }
    }
}
