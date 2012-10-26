package sps.text;

import sps.core.Point2;
import sps.core.Spx;

import java.util.ArrayList;
import java.util.List;

public class TextHandler {
    private final DefaultHudText[] defaultPool = new DefaultHudText[100];
    private int defaultIndex = 0;

    private final List<Text> _contents = new ArrayList<Text>();

    public TextHandler() {
        for (int ii = 0; ii < defaultPool.length; ii++) {
            defaultPool[ii] = new DefaultHudText(1f);
        }
    }

    public void writeDefault(String contents, int x, int y, Point2 origin) {
        defaultPool[defaultIndex].reset(contents, x, y, origin);
        add(defaultPool[defaultIndex]);
        defaultIndex = (defaultIndex + 1) % defaultPool.length;
    }

    public void add(Text textToAdd) {
        if (!_contents.contains(textToAdd)) {
            _contents.add(textToAdd);
        }
    }

    public void clear() {
        _contents.clear();
    }

    public void update() {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).update() <= 0) {
                _contents.remove(_contents.get(ii));
                ii--;
            }
        }
    }

    public void draw() {

        if (Spx.Renderer != null) {
            for (Text component : _contents) {
                component.draw();
            }
        }
    }
}
