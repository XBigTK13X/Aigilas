package aigilas.states;

import aigilas.Common;
import sps.audio.MusicPlayer;
import sps.bridge.Commands;
import sps.core.Point2;
import sps.graphics.Renderer;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;
import sps.text.TextPool;

public class GameWinState implements State {
    public GameWinState() {

    }

    @Override
    public void draw() {
    }

    @Override
    public void update() {
        if (Input.isActive(Commands.get(Common.Commands.Confirm), 0, true)) {
            StateManager.loadState(new LoadingState());
        }
    }

    @Override
    public void load() {
        MusicPlayer.get().stop();
        TextPool.get().write("Victory to you!", Renderer.get().center());
        TextPool.get().write("Press SPACEBAR to restart", new Point2(200, 200));
    }

    @Override
    public void unload() {

    }
}
