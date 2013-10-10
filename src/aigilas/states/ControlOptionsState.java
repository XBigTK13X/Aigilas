package aigilas.states;

import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.apache.commons.io.FileUtils;
import sps.core.Logger;
import sps.states.StateManager;

import java.io.File;

public class ControlOptionsState extends MenuState {
    public ControlOptionsState() {
        final SelectableButton configControllerBtn = new SelectableButton("Setup Controller", UiAssets.getButtonStyle());
        configControllerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                setupController();
            }
        });

        final SelectableButton keyboardConfigBtn = new SelectableButton("Setup Keyboard", UiAssets.getButtonStyle());
        keyboardConfigBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                setupKeyboard();
            }
        });

        final SelectableButton resetControlsBtn = new SelectableButton("Reset Controls", UiAssets.getButtonStyle());
        resetControlsBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                resetControls();
            }
        });

        final SelectableButton backBtn = new SelectableButton("Back", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new OptionsState());
            }
        });


        add(configControllerBtn);
        add(keyboardConfigBtn);
        table.row();
        add(resetControlsBtn);
        add(backBtn);
    }

    private void setupController() {
        StateManager.loadState(new SetupControllerState());
    }

    private void setupKeyboard() {
        StateManager.loadState(new SetupKeyboardState());
    }

    private void resetControls() {
        try {
            FileUtils.copyFile(new File("assets/data/default_input.cfg"), new File("assets/data/input.cfg"));
        }
        catch (Exception e) {
            Logger.exception(e, false);
        }
    }

    @Override
    public String getName() {
        return "ControlOptionsState";
    }
}
