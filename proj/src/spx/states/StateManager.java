package spx.states;

public class StateManager {
    private static State _state;

    public static void LoadState(State state) {
        _state = state;
        _state.LoadContent();
    }

    public static void Draw() {
        _state.Draw();
    }

    public static void LoadContent() {
        _state.LoadContent();
    }

    public static void Update() {
        _state.Update();
    }
}
