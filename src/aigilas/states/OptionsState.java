package aigilas.states;

import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.states.StateManager;

public class OptionsState extends MenuState {
    public OptionsState() {
        final SelectableButton videoOptions = new SelectableButton("Video", UiAssets.getButtonStyle());
        videoOptions.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new VideoOptionsState());
            }
        });

        final SelectableButton audioOptions = new SelectableButton("Audio", UiAssets.getButtonStyle());
        audioOptions.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new AudioOptionsState());
            }
        });

        final SelectableButton controlOptions = new SelectableButton("Controls", UiAssets.getButtonStyle());
        controlOptions.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new ControlOptionsState());
            }
        });


        final SelectableButton backBtn = new SelectableButton("Back", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new MainMenuState());
            }
        });

        add(videoOptions);
        add(audioOptions);
        add(controlOptions);
        table.row();
        add(backBtn);
    }

    @Override
    public String getName() {
        return "OptionsState";
    }
}
