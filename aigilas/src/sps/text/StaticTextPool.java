package sps.text;

import sps.core.Point2;

import java.util.ArrayList;
import java.util.List;

public class StaticTextPool {

    private static StaticTextPool instance;

    public static StaticTextPool get() {
        if (instance == null) {
            instance = new StaticTextPool();
        }
        return instance;
    }


    private List<StaticText> texts = new ArrayList<StaticText>();
    private int index = 0;

    private StaticTextPool() {
        for (int ii = 0; ii < 1000; ii++) {
            texts.add(new StaticText());
        }
    }

    public void clear() {
        for (StaticText text : texts) {
            text.hide();
        }
    }

    public StaticText write(String message, Point2 position) {
        return write(message, position, StaticText.NotTimed);
    }

    public StaticText write(String message, Point2 position, float lifeInSeconds) {
        return write(message, position, lifeInSeconds, TextEffects.None);
    }

    public StaticText write(String message, Point2 position, float lifeInSeconds, TextEffect effect) {
        StaticText result = texts.get(index);
        result.reset(position, message, 1, lifeInSeconds, effect);
        index = (index + 1) % texts.size();
        return result;
    }

    public void draw() {
        for (StaticText text : texts) {
            if (text.isVisible()) {
                text.draw();
            }
        }
    }

    public void update() {
        for (StaticText text : texts) {
            if (text.isVisible()) {
                text.update();
            }
        }
    }
}
