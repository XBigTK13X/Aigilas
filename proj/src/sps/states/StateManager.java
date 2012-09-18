package sps.states;

public class StateManager {
    private static State _state;

    public static void loadState(State state) {
        if(_state != null){
            _state.unload();
        }
        _state = state;
        _state.loadContent();
    }

    public static void draw() {
        _state.draw();
    }

    public static void loadContent() {
        _state.loadContent();
    }

    public static void update() {
        _state.update();
    }
}
