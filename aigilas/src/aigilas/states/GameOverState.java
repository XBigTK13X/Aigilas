package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.core.SpxManager;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;

public class GameOverState implements State {
    public GameOverState() {
    }

    @Override
    public void draw() {
        SpxManager.Renderer.drawString("Game Over", SpxManager.getCenter(), Color.WHITE, 1, DrawDepth.ActionText);
        SpxManager.Renderer.drawString("Press SPACEBAR to restart", new Point2(200, 200), Color.WHITE, 1, DrawDepth.ActionText);
    }

    @Override
    public void update() {
        if (Input.isActive(Commands.Confirm, 0, true)) {
            StateManager.loadState(new GameplayState());
        }
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void unload() {

    }
}
