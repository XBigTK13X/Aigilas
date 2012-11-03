package sps.states;

import sps.text.StaticTextPool;

public class StateManager {
    private static State _state;

    public static void loadState(State state) {
        if (_state != null) {
            _state.unload();
        }
        StaticTextPool.get().clear();
        _state = state;
        _state.load();
    }

    public static void draw() {
        _state.draw();
    }

    public static void loadContent() {
        _state.load();
    }

    public static void update() {
        _state.update();
    }
}
