package sps.text;

import sps.core.RNG;

public class TextEffects {
    public static final Fountain Fountain = new Fountain();
    public static final None None = new None();

    private static class Fountain implements TextEffect {
        @Override
        public void init(StaticText text) {
            text.setVel(0, RNG.next(10, 20));
            text.setAccel(RNG.next(-25, 25) / 100f, -RNG.next(50, 100) / 100f);
        }

        @Override
        public void update(StaticText text) {
            text.accel();
        }
    }

    private static class None implements TextEffect {
        @Override
        public void init(StaticText text) {
        }

        @Override
        public void update(StaticText text) {
        }
    }
}
