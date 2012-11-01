package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Color;
import sps.audio.MusicPlayer;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.graphics.Renderer;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;

public class GameWinState implements State {
    public GameWinState() {
        MusicPlayer.get().stop();
    }

    @Override
    public void draw() {
        Renderer.get().drawString("Victory to you!", Renderer.get().getCenter(), Color.WHITE, 1, DrawDepth.ActionText);
        Renderer.get().drawString("Press SPACEBAR to restart", new Point2(200, 200), Color.WHITE, 1, DrawDepth.ActionText);
    }

    @Override
    public void update() {
        if (Input.isActive(Commands.Confirm, 0, true)) {
            StateManager.loadState(new LoadingState());
        }
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void unload() {

    }
}
