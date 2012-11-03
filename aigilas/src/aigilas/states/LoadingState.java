package aigilas.states;

import com.badlogic.gdx.graphics.Color;
import sps.audio.MusicPlayer;
import sps.bridge.DrawDepth;
import sps.graphics.Renderer;
import sps.states.State;
import sps.states.StateManager;

public class LoadingState implements State {
    public LoadingState() {

    }

    @Override
    public void draw() {

    }

    int delay = 10;
    boolean fired = false;

    @Override
    public void update() {
        delay--;
        if (delay < 0 && !fired) {
            StateManager.loadState(new GameplayState());
            fired = true;
        }
    }

    @Override
    public void load() {
        MusicPlayer.get().stop();
        Renderer.get().drawString("Preparing for adventure!", Renderer.get().center(), Color.WHITE, 1, DrawDepth.ActionText);
    }

    @Override
    public void unload() {

    }
}
