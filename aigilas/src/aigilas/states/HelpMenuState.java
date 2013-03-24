package aigilas.states;

import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.states.StateManager;
import sps.util.PdfViewer;

public class HelpMenuState extends MenuState {

    public HelpMenuState() {
        final SelectableButton backstoryBtn = new SelectableButton("Backstory", UiAssets.getButtonStyle());
        backstoryBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                PdfViewer.open("Backstory");
            }
        });

        final SelectableButton instructionsBtn = new SelectableButton("Instructions", UiAssets.getButtonStyle());
        instructionsBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                PdfViewer.open("Gameplay Instructions");
            }
        });

        final SelectableButton defaultControlsButton = new SelectableButton("Default Controls", UiAssets.getButtonStyle());
        defaultControlsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                PdfViewer.open("Command Quick Reference");
            }
        });

        final SelectableButton backBtn = new SelectableButton("Main Menu", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new MainMenuState());
            }
        });

        add(backstoryBtn);
        add(instructionsBtn);
        add(defaultControlsButton);
        table.row();
        add(backBtn);
    }

    @Override
    public String getName() {
        return "HelpMenuState";
    }
}
