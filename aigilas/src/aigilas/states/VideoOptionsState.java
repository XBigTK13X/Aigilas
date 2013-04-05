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

public class VideoOptionsState extends MenuState {
    public VideoOptionsState() {
        final SelectableButton fullScreenBtn = new SelectableButton("Fullscreen", UiAssets.getButtonStyle());
        fullScreenBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                toggleFullScreen();
            }
        });

        final SelectableButton renderStratBtn = new SelectableButton("Fixed Render", UiAssets.getButtonStyle());
        renderStratBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                toggleRenderStrategy();
            }
        });

        final SelectableButton backBtn = new SelectableButton("Back", UiAssets.getButtonStyle());
        backBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                StateManager.loadState(new OptionsState());
            }
        });

        add(fullScreenBtn);
        add(renderStratBtn);
        table.row();
        add(backBtn);
    }

    private void toggleFullScreen() {
        Renderer.get().toggleFullScreen();
        StateManager.loadState(new VideoOptionsState());
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

    @Override
    public String getName() {
        return "VideoOptionsState";
    }
}
