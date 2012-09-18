package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Texture;
import sps.core.Point2;
import sps.core.SpxManager;
import sps.io.Input;
import sps.states.State;
import sps.states.StateManager;

public class GameOverState implements State {
    private final Texture _gameOver;

    public GameOverState() {
        _gameOver = SpxManager.getGameOverAsset();
    }

    @Override
    public void draw() {
        SpxManager.Renderer.draw(_gameOver, Point2.Zero);
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
