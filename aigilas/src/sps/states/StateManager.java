package sps.states;

import sps.text.TextPool;

public class StateManager {
    private static State _state;

    public static void loadState(State state) {
        if (_state != null) {
            _state.unload();
        }
        TextPool.get().clear();
        _state = state;
        //$$$ Logger.info("=== Loading new state: " + state.getName());
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

    public static void asyncUpdate() {
        _state.asyncUpdate();
    }

    public static void resize(int width, int height) {
        _state.resize(width, height);
    }
}
