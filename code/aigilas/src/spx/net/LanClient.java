package spx.net;

import java.net.Socket;
import java.util.HashMap;

import spx.core.RNG;
import spx.core.Settings;
import spx.devtools.DevConsole;

public class LanClient implements IClient {
	 = Client<->Server
	private Message _message;
	private MessageHandler _comm;
	private int _heartBeat = 30;

	 = Client<->Game
	private Integer _initialPlayerIndex;
	private boolean _isGameStarting;
	private boolean _dungeonHasLoaded = false;
	private boolean _isConnected;
	private final HashMap<Integer, HashMap<Integer, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Integer, Boolean>>();

	public LanClient() {
		if (Settings.Get().clientVerbose) {
			System.out.println("CLIENT: Attempting to make a new connection");
		}
		for (int ii = 0; ii < Message.PlayerMax; ii++) {
			_playerStatus.put(ii, new HashMap<Integer, Boolean>());
			for (int jj = 0; jj < Message.CommandMax; jj++) {
				_playerStatus.get(ii).put(jj, false);
			}
		}
		try {
			Socket server = new Socket(Settings.Get().serverIp, Settings.Get().port);
			_comm = new MessageHandler(server);
			_comm.owner = "CLIENT";
			SendMessage(Message.CreateInit(0, 0));
			AwaitReply(MessageTypes.CONNECT);
			HandleResponse(_message);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	 = Client<->Game communication
	public boolean IsGameStarting() {
		return _isGameStarting;
	}

	public boolean IsConnected() {
		return _isConnected;
	}

	public void HeartBeat() {
		if (!_dungeonHasLoaded) {
			_heartBeat--;
			if (_heartBeat <= 0) {
				if (Settings.Get().clientVerbose)
					System.out.println("CLIENT: Heartbeating...");
				SendMessage(Message.CreateHeartBeat());
				_heartBeat = 15;
			}
		}
	}

	public void DungeonHasLoaded() {
		System.out.println("CLIENT: Dungeon has finished loading...");
		_dungeonHasLoaded = true;
	}

	public boolean NextTurn() {
		Update();
		if (_message != null) {
			if (_message.MessageType == MessageTypes.SYNC_STATE) {
				if (Settings.Get().clientVerbose) {
					DevConsole.Get().Add("CLIENT: Synced:  " + _message.TurnCount + ". Seeding:  " + _message.RngSeed);
				}
				RNG.Seed(_message.RngSeed);
				_heartBeat = 15;
			}
			return _message.MessageType == MessageTypes.SYNC_STATE;
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
		return _initialPlayerIndex;
	}

	public void PrepareForNextTurn() {
		SendMessage(Message.CreateReadyForNextTurn());
	}

	 = Client<->Server communication
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
			SendMessage(Message.CreateMovement(command, playerIndex, isActive));
		}
	}

	int _playerCount = 0;

	public int GetPlayerCount() {
		if (_playerCount == 0) {
			SendMessage(Message.CreatePlayerCount(0));
			AwaitReply(MessageTypes.PLAYER_COUNT);
			_playerCount = _message.PlayerCount;
		}
		return _playerCount;
	}

	public void StartGame() {
		SendMessage(Message.Create(MessageTypes.START_GAME));
	}

	private void SendMessage(Message contents) {
		if (Settings.Get().clientVerbose) {
			System.out.println("CLIENT: Sending message -> " + contents.MessageType);
		}
		contents.PlayerIndex = _initialPlayerIndex;
		contents.LocalPort = _comm.getLocalPort();
		_comm.sendOutboundMessage(contents);
	}

	 = If the server doesn't reply at some point with the messageType you expect
	 = Then the client will hang extends an infinite loop.
	private void AwaitReply(MessageTypes messageType) {
		if (Settings.Get().clientVerbose) {
			System.out.println("CLIENT: Waiting for " + messageType);
		}
		while (true) {
			_message = _comm.readInboundMessage();
			if (_message != null) {
				if (_message.MessageType == messageType) {
					if (Settings.Get().clientVerbose) {
						System.out.println("CLIENT: Right message received");
					}
					return;
				}
				else {
					if (Settings.Get().clientVerbose) {
						System.out.println("CLIENT: Wrong message received:  " + _message.MessageType + "; Expected:  " + messageType);
					}
					HandleResponse(_message);
				}
			}
		}
	}

	public void Update() {
		_message = _comm.readInboundMessage();
		if (_message != null) {
			HandleResponse(_message);
		}
	}

	private void HandleResponse(Message contents) {
		switch (contents.MessageType) {
			case CONNECT:
				if (Settings.Get().clientVerbose) {
					System.out.println("CLIENT: Handshake successful. Starting player id extends  " + contents.PlayerIndex);
				}
				RNG.Seed(contents.RngSeed);
				_initialPlayerIndex = (int) contents.PlayerCount;
				_isConnected = true;
				break;
			case START_GAME:
				System.out.println("CLIENT: Start game reply has been received");
				_isGameStarting = true;
				break;
			case SYNC_STATE:
				if (Settings.Get().clientVerbose) {
					System.out.println("CLIENT: Input state received");
				}
				contents.ReadPlayerState(_playerStatus);
				break;
			default:
				if (Settings.Get().clientVerbose) {
					System.out.println("CLIENT: Unknown message type received -> " + _message.MessageType);
				}
				break;
		}
	}

	public void Close() {
		System.out.println("CLIENT: Shutting down");
	}
}
