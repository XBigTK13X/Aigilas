package aigilas.states;

import aigilas.net.Client;
import sps.audio.MusicPlayer;
import sps.core.Logger;
import sps.graphics.Renderer;
import sps.states.State;
import sps.states.StateManager;
import sps.text.TextPool;

public class LoadingState implements State {
    public LoadingState() {
        Logger.info(Client.get().getFirstPlayerIndex() + " is the player index");
        Logger.info(Client.get().getPlayerCount() + " is the player count");
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
        TextPool.get().write("Preparing for adventure!", Renderer.get().center());
    }

    @Override
    public void unload() {

    }
}
