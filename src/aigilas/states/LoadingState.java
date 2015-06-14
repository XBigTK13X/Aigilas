package aigilas.states;

import sps.audio.MusicPlayer;
import sps.core.RNG;
import sps.graphics.Renderer;
import sps.states.State;
import sps.states.StateManager;
import sps.text.TextPool;

public class LoadingState implements State {
    private String[] sayings = {
            "Preparing for adventure!",
            "Are you ready to welcome death?",
            "Punching acolytes won't get you far.",
            "Combining the elements is crucial to your victory.",
            "Put it off forever, or not at all.",
            "You may delay, but time will not."
    };

    public LoadingState() {
    }

    //@Override
    public void draw() {

    }

    //@Override
    public void update() {
        StateManager.loadState(new GameplayState());
    }

    //@Override
    public void asyncUpdate() {
    }

    //@Override
    public void load() {
        MusicPlayer.get().stop();
        TextPool.get().write(sayings[RNG.next(0, sayings.length, false)], Renderer.get().center().add(-500, 0));
    }

    //@Override
    public void unload() {

    }

    //@Override
    public String getName() {
        return "Loading state";
    }

    //@Override
    public void resize(int width, int height) {
    }
}
