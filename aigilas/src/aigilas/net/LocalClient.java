package aigilas.net;

import aigilas.Config;
import com.badlogic.gdx.Gdx;
import sps.bridge.Command;
import sps.core.RNG;
import sps.io.CommandState;

public class LocalClient extends IClient {
    private boolean _isGameStarting;
    private float _turnTimer = 0;
    private final CommandState state = new CommandState();

    public LocalClient() {

    }

    public boolean isGameStarting() {
        return _isGameStarting;
    }

    public boolean isConnected() {
        return true;
    }

    public boolean nextTurn() {
        update();
        if (_turnTimer >= Config.get().turnTime) {
            _turnTimer = 0;
            return true;
        }
        return false;
    }

    public int getFirstPlayerIndex() {
        return 0;
    }

    public boolean isActive(Command command, int playerIndex) {
        return state.isActive(playerIndex, command);
    }

    public void setState(Command command, int playerIndex, boolean isActive) {
        state.setState(playerIndex, command, isActive);
    }

    public int getPlayerCount() {
        return 1;
    }

    public void startGame() {
        RNG.seed((int) System.currentTimeMillis());
        _isGameStarting = true;
    }

    public void update() {
        _turnTimer += Gdx.graphics.getDeltaTime();
    }

    public void close() {
    }

    public void dungeonHasLoaded() {

    }

    public void heartBeat() {
    }

    public void prepareForNextTurn() {
    }
}
