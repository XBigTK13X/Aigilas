package aigilas.states;

import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.graphics.FrameStrategy;
import sps.graphics.RenderStrategy;
import sps.graphics.Renderer;
import sps.graphics.StretchStrategy;
import sps.states.StateManager;

public class OptionsState extends MenuState {
    public OptionsState() {
        final SelectableButton fullScreenBtn = new SelectableButton("Toggle Fullscreen", UiAssets.getButtonStyle());
        fullScreenBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                toggleFullScreen();
            }
        });

        final SelectableButton renderStratBtn = new SelectableButton("Toggle Fixed Aspect Ratio", UiAssets.getButtonStyle());
        renderStratBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                toggleRenderStrategy();
            }
        });

        final SelectableButton configControllerBtn = new SelectableButton("Setup Controller", UiAssets.getButtonStyle());
        renderStratBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                setupController();
            }
        });

        final SelectableButton keyboardConfigBtn = new SelectableButton("Setup Controller", UiAssets.getButtonStyle());
        renderStratBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                setupKeyboard();
            }
        });

        final SelectableButton backBtn = new SelectableButton("Main Menu", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new MainMenuState());
            }
        });

        add(fullScreenBtn);
        table.row();
        add(renderStratBtn);
        table.row();
        add(configControllerBtn);
        table.row();
        add(keyboardConfigBtn);
        table.row();
        add(backBtn);
    }

    private void toggleFullScreen() {
        Renderer.get().toggleFullScreen();
        StateManager.loadState(new OptionsState());
    }

    private RenderStrategy current;

    private void toggleRenderStrategy() {
        if (current == null || current.getClass() == FrameStrategy.class) {
            current = new StretchStrategy();
        }
        else {
            current = new FrameStrategy();
        }
        Renderer.get().setStrategy(current);
    }

    private void setupController() {
        StateManager.loadState(new SetupControllerState());
    }

    private void setupKeyboard() {
        StateManager.loadState(new SetupKeyboardState());
    }
}
