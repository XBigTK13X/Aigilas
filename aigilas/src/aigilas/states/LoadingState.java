package aigilas.states;

import sps.audio.MusicPlayer;
import sps.core.Logger;
import sps.graphics.Renderer;
import sps.states.State;
import sps.states.StateManager;
import sps.text.TextPool;

public class LoadingState implements State {
    public LoadingState() {
    }

    @Override
    public void draw() {

    }

    boolean fired = false;

    @Override
    public void update() {
        if (!fired) {
            Logger.info("Updating the load state");
            StateManager.loadState(new GameplayState());
            fired = true;
        }
    }

    @Override
    public void asyncUpdate() {
    }

    @Override
    public void load() {
        MusicPlayer.get().stop();
        TextPool.get().write("Preparing for adventure!", Renderer.get().center());
    }

    @Override
    public void unload() {

    }
}
