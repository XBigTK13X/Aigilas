package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Texture;
import spx.core.Point2;
import spx.core.SpxManager;
import spx.io.Input;
import spx.states.State;
import spx.states.StateManager;

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
