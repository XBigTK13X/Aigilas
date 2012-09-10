package spx.net;

import java.util.HashMap;

import spx.core.RNG;
import spx.core.Settings;

import com.badlogic.gdx.Gdx;

public class LocalClient implements IClient {

	 = Client<->Game
	private boolean _isGameStarting;
	private float _turnTimer = 0;
	private boolean _isConnected;
	private final HashMap<Integer, HashMap<Integer, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Integer, Boolean>>();

	private final int maxPlayers = 1;

	public LocalClient() {
		if (Settings.Get().clientVerbose) {
			System.out.println("CLIENT: Starting up");
		}
		for (int ii = 0; ii < maxPlayers; ii++) {
			_playerStatus.put(ii, new HashMap<Integer, Boolean>());
			for (int jj = 0; jj < Message.CommandMax; jj++) {
				_playerStatus.get(ii).put(jj, false);
			}
		}
	}

	public boolean IsGameStarting() {
		return _isGameStarting;
	}

	public boolean IsConnected() {
		return _isConnected;
	}

	public boolean NextTurn() {
		Update();
		if (_turnTimer >= Settings.Get().turnTime) {
			_turnTimer = 0;
			return true;
		}
		return false;
	}

	private void InitPlayer(int playerIndex, Commands command) {
		if (!_playerStatus.containsKey(playerIndex)) {
			_playerStatus.put(playerIndex, new HashMap<Integer, Boolean>());
		}
		if (!_playerStatus.get(playerIndex).containsKey(command)) {
			_playerStatus.get(playerIndex).put(command, false);
		}
	}

	public int GetFirstPlayerIndex() {
		return 0;
	}

	public boolean IsActive(Commands command, int playerIndex) {
		if (_playerStatus.containsKey(playerIndex) && _playerStatus.get(playerIndex).containsKey(command)) {
			return _playerStatus.get(playerIndex).get(command);
		}
		return false;
	}

	public void SetState(Commands command, int playerIndex, boolean isActive) {
		InitPlayer(playerIndex, command);
		if (_playerStatus.get(playerIndex).get(command) != isActive) {
			if (Settings.Get().clientVerbose) {
				System.out.println(String.format("CLIENT: Moves extends  CMD(%s) PI(%s) AC(%s)", command, playerIndex, isActive));
			}
			_playerStatus.get(playerIndex).put(command, isActive);
		}
	}

	int _playerCount = 0;

	public int GetPlayerCount() {
		if (_playerCount == 0) {
			_playerCount = _playerStatus.keySet().size();
		}
		return _playerCount;
	}

	public void StartGame() {
		RNG.Seed((int) System.currentTimeMillis());
		_isConnected = true;
		_isGameStarting = true;
	}

	public void Update() {
		_turnTimer += Gdx.graphics.getDeltaTime();
	}

	public void Close() {
	}

	public void DungeonHasLoaded() {

	}

	public void HeartBeat() {
	}

	public void PrepareForNextTurn() {
	}
}
