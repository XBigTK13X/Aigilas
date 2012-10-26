package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Color;
import sps.audio.MusicPlayer;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.core.Spx;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;

public class GameLoseState implements State {
    public GameLoseState() {
        MusicPlayer.get().stop();
    }

    @Override
    public void draw() {
        Spx.Renderer.drawString("Game Over", Spx.getCenter(), Color.WHITE, 1, DrawDepth.ActionText);
        Spx.Renderer.drawString("Press SPACEBAR to restart", new Point2(200, 200), Color.WHITE, 1, DrawDepth.ActionText);
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
