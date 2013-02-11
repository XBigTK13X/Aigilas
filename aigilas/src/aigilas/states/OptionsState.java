package aigilas.states;

import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.apache.commons.io.FileUtils;
import sps.core.Logger;
import sps.graphics.FrameStrategy;
import sps.graphics.RenderStrategy;
import sps.graphics.Renderer;
import sps.graphics.StretchStrategy;
import sps.states.StateManager;

import java.io.File;

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
        configControllerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                setupController();
            }
        });

        final SelectableButton keyboardConfigBtn = new SelectableButton("Setup Controller", UiAssets.getButtonStyle());
        keyboardConfigBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                setupKeyboard();
            }
        });

        final SelectableButton resetControlsBtn = new SelectableButton("Reset Controls", UiAssets.getButtonStyle());
        keyboardConfigBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                resetControls();
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
        add(keyboardConfigBtn);
        table.row();
        add(resetControlsBtn);
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

    private void resetControls(){
        try{
            FileUtils.copyFile(new File("assets/data/default_input.cfg"),new File("assets/data/input.cfg"));
        }
        catch(Exception e){
            Logger.exception(e,false);
        }
    }
}
