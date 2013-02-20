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

    @Override
    public void update() {
        Logger.info(LoadingState.class.toString());
        StateManager.loadState(new GameplayState());
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
