package aigilas.states;

import aigilas.Aigilas;
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
        if (Input.get().isActive(Commands.get(Aigilas.Commands.Confirm), 0, true)) {
            StateManager.loadState(new MainMenuState());
        }
    }

    @Override
    public void asyncUpdate() {
    }

    @Override
    public void load() {
        MusicPlayer.get().stop();
        TextPool.get().write("Victory to you!", Renderer.get().center());
        TextPool.get().write("Press SPACEBAR to continue", new Point2(200, 200));
    }

    @Override
    public void unload() {

    }

    @Override
    public String getName() {
        return "GameWinState";
    }
}
