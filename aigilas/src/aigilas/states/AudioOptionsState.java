package aigilas.states;

import aigilas.ui.SelectableButton;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sps.audio.MusicPlayer;
import sps.graphics.Renderer;
import sps.states.StateManager;

public class AudioOptionsState extends MenuState {
    public AudioOptionsState() {
        final SelectableButton toggleMusicBtn = new SelectableButton("Toggle Music Mute", UiAssets.getButtonStyle());
        toggleMusicBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                MusicPlayer.get().toggle();
            }
        });

        final SelectableButton backBtn = new SelectableButton("Back", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new MainMenuState());
            }
        });

        add(toggleMusicBtn);
        table.row();
        add(backBtn);
    }

    private void toggleFullScreen() {
        Renderer.get().toggleFullScreen();
        StateManager.loadState(new AudioOptionsState());
    }

    @Override
    public String getName() {
        return "AudioOptionsState";
    }
}
