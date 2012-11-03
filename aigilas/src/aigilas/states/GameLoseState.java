package aigilas.states;

import aigilas.management.Commands;
import sps.audio.MusicPlayer;
import sps.core.Point2;
import sps.graphics.Renderer;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;
import sps.text.StaticTextPool;

public class GameLoseState implements State {
    public GameLoseState() {
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {
        if (Input.isActive(Commands.Confirm, 0, true)) {
            StateManager.loadState(new LoadingState());
        }
    }

    @Override
    public void load() {
        MusicPlayer.get().stop();
        StaticTextPool.get().write("Game Over", Renderer.get().center());
        StaticTextPool.get().write("Press SPACEBAR to restart", new Point2(200, 200));
    }

    @Override
    public void unload() {

    }
}
