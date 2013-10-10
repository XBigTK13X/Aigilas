package sps.text;

import com.badlogic.gdx.graphics.Color;
import sps.core.Point2;

import java.util.ArrayList;
import java.util.List;

public class TextPool {

    private static TextPool instance;

    public static TextPool get() {
        if (instance == null) {
            instance = new TextPool();
        }
        return instance;
    }


    private List<Text> texts = new ArrayList<Text>();
    private int index = 0;

    private TextPool() {
        for (int ii = 0; ii < 1000; ii++) {
            texts.add(new Text());
        }
    }

    public void clear() {
        for (Text text : texts) {
            text.hide();
        }
    }

    public Text write(String message, Point2 position) {
        return write(message, position, Text.NotTimed);
    }

    public Text write(String message, Point2 position, float lifeInSeconds) {
        return write(message, position, lifeInSeconds, TextEffects.None);
    }

    public Text write(String message, Point2 position, float lifeInSeconds, TextEffect effect, Color color, float scale) {
        Text result = texts.get(index);
        result.reset(position, message, 1, lifeInSeconds, effect);
        result.setColor(color);
        result.setScale(scale);
        index = (index + 1) % texts.size();
        return result;
    }

    public Text write(String message, Point2 position, float lifeInSeconds, TextEffect effect) {
        return write(message, position, lifeInSeconds, effect, Color.WHITE, 1f);
    }

    public void draw() {
        for (Text text : texts) {
            if (text.isVisible()) {
                text.draw();
            }
        }
    }

    public void update() {
        for (Text text : texts) {
            if (text.isVisible()) {
                text.update();
            }
        }
    }
}
