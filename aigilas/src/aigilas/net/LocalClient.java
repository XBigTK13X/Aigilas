package aigilas.net;

import aigilas.Config;
import com.badlogic.gdx.Gdx;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.core.Logger;
import sps.core.RNG;
import sps.core.Settings;

import java.util.HashMap;

public class LocalClient implements IClient {
    private boolean _isGameStarting;
    private float _turnTimer = 0;
    private boolean _isConnected;
    private final HashMap<Integer, HashMap<Command, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Command, Boolean>>();

    private final int maxPlayers = 1;

    public LocalClient() {
        for (int ii = 0; ii < maxPlayers; ii++) {
            _playerStatus.put(ii, new HashMap<Command, Boolean>());
            for (Command command : Commands.values()) {
                _playerStatus.get(ii).put(command, false);
            }
        }
    }

    public boolean isGameStarting() {
        return _isGameStarting;
    }

    public boolean isConnected() {
        return _isConnected;
    }

    public boolean nextTurn() {
        update();
        if (_turnTimer >= Config.get().turnTime) {
            _turnTimer = 0;
            return true;
        }
        return false;
    }

    private void initPlayer(int playerIndex, Command command) {
        if (!_playerStatus.containsKey(playerIndex)) {
            _playerStatus.put(playerIndex, new HashMap<Command, Boolean>());
        }
        if (!_playerStatus.get(playerIndex).containsKey(command)) {
            _playerStatus.get(playerIndex).put(command, false);
        }
    }

    public int getFirstPlayerIndex() {
        return 0;
    }

    public boolean isActive(Command command, int playerIndex) {
        if (_playerStatus.containsKey(playerIndex) && _playerStatus.get(playerIndex).containsKey(command)) {
            return _playerStatus.get(playerIndex).get(command);
        }
        return false;
    }

    public void setState(Command command, int playerIndex, boolean isActive) {
        initPlayer(playerIndex, command);
        if (_playerStatus.get(playerIndex).get(command) != isActive) {
            _playerStatus.get(playerIndex).put(command, isActive);
        }
    }

    int _playerCount = 0;

    public int getPlayerCount() {
        if (_playerCount == 0) {
            _playerCount = _playerStatus.keySet().size();
        }
        return _playerCount;
    }

    public void startGame() {
        RNG.seed((int) System.currentTimeMillis());
        _isConnected = true;
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
