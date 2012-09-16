package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Texture;
import spx.core.Point2;
import spx.core.SpxManager;
import spx.io.Input;
import spx.states.State;
import spx.states.StateManager;

public class GameOverState implements State {
    private final Texture _menuBase;

    public GameOverState() {
        _menuBase = SpxManager.getGameOverAsset();
    }

    @Override
    public void draw() {
        float x = (SpxManager.WindowWidth - _menuBase.getWidth()) / 2;
        float y = (SpxManager.WindowHeight - _menuBase.getHeight()) / 2;

        SpxManager.Renderer.draw(_menuBase, new Point2(x, y));
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
}
