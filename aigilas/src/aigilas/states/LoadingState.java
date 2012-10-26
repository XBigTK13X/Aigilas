package aigilas.states;

import com.badlogic.gdx.graphics.Color;
import sps.audio.MusicPlayer;
import sps.bridge.DrawDepth;
import sps.core.Spx;
import sps.states.State;
import sps.states.StateManager;

public class LoadingState implements State {
    public LoadingState() {
        MusicPlayer.get().stop();
    }

    @Override
    public void draw() {
        Spx.Renderer.drawString("Preparing for adventure!", Spx.getCenter(), Color.WHITE, 1, DrawDepth.ActionText);
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
    public void loadContent() {

    }

    @Override
    public void unload() {

    }
}
