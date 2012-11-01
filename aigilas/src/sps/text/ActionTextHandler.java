package sps.text;

import sps.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class ActionTextHandler {
    private final ActionText[] defaultPool = new ActionText[100];
    private int defaultIndex = 0;

    private final List<Text> _contents = new ArrayList<Text>();

    public ActionTextHandler() {
        for (int ii = 0; ii < defaultPool.length; ii++) {
            defaultPool[ii] = new ActionText();
        }
    }

    public void writeAction(String contents, int lifespan, int x, int y) {
        defaultPool[defaultIndex].reset(contents, lifespan, x, y);
        add(defaultPool[defaultIndex]);
        defaultIndex = (defaultIndex + 1) % defaultPool.length;
    }

    public void add(Text textToAdd) {
        if (!_contents.contains(textToAdd)) {
            _contents.add(textToAdd);
            TextManager.add(textToAdd);
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

        if (Renderer.get() != null) {
            for (Text component : _contents) {
                component.draw();
            }
        }
    }
}
