package sps.net;

import sps.core.Commands;
import com.badlogic.gdx.Gdx;
import sps.core.Logger;
import sps.core.RNG;
import sps.core.Settings;

import java.util.HashMap;

public class LocalClient implements IClient {
    private boolean _isGameStarting;
    private float _turnTimer = 0;
    private boolean _isConnected;
    private final HashMap<Integer, HashMap<Commands, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Commands, Boolean>>();

    private final int maxPlayers = 1;

    public LocalClient() {
        if (Settings.get().clientVerbose) {
            Logger.client("CLIENT: Starting up");
        }
        for (int ii = 0; ii < maxPlayers; ii++) {
            _playerStatus.put(ii, new HashMap<Commands, Boolean>());
            for (Commands command : Commands.values()) {
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
        if (_turnTimer >= Settings.get().turnTime) {
            _turnTimer = 0;
            return true;
        }
        return false;
    }

    private void initPlayer(int playerIndex, Commands command) {
        if (!_playerStatus.containsKey(playerIndex)) {
            _playerStatus.put(playerIndex, new HashMap<Commands, Boolean>());
        }
        if (!_playerStatus.get(playerIndex).containsKey(command)) {
            _playerStatus.get(playerIndex).put(command, false);
        }
    }

    public int getFirstPlayerIndex() {
        return 0;
    }

    public boolean isActive(Commands command, int playerIndex) {
        if (_playerStatus.containsKey(playerIndex) && _playerStatus.get(playerIndex).containsKey(command)) {
            return _playerStatus.get(playerIndex).get(command);
        }
        return false;
    }

    public void setState(Commands command, int playerIndex, boolean isActive) {
        initPlayer(playerIndex, command);
        if (_playerStatus.get(playerIndex).get(command) != isActive) {
            if (Settings.get().clientVerbose) {
                Logger.client(String.format("CLIENT: Moves extends  CMD(%s) PI(%s) AC(%s)", command, playerIndex, isActive));
            }
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
