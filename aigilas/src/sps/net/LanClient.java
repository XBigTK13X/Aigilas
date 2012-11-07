package sps.net;

import sps.bridge.Command;
import sps.bridge.Commands;
import sps.core.DevConsole;
import sps.core.Logger;
import sps.core.RNG;
import sps.core.Settings;

import java.net.Socket;
import java.util.HashMap;

public class LanClient implements IClient {
    // Client <-> Server
    private Message _message;
    private MessageHandler _comm;
    private int _heartBeat = 30;

    // Client <-> Game
    private Integer _initialPlayerIndex;
    private boolean _isGameStarting;
    private boolean _dungeonHasLoaded = false;
    private boolean _isConnected;
    private final HashMap<Integer, HashMap<Command, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Command, Boolean>>();

    public LanClient() {
        if (Settings.get().clientVerbose) {
            Logger.client("CLIENT: Attempting to make a new connection");
        }
        for (int ii = 0; ii < Message.PlayerMax; ii++) {
            _playerStatus.put(ii, new HashMap<Command, Boolean>());
            for (Command command : Commands.values()) {
                _playerStatus.get(ii).put(command, false);
            }
        }
        try {
            Socket server = new Socket(Settings.get().serverIp, Settings.get().port);
            _comm = new MessageHandler(server);
            _comm.owner = "CLIENT";
            sendMessage(Message.createInit(0, 0));
            awaitReply(MessageTypes.Connect);
            handleResponse(_message);
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

    // Client <-> Game communication
    public boolean isGameStarting() {
        return _isGameStarting;
    }

    public boolean isConnected() {
        return _isConnected;
    }

    public void heartBeat() {
        if (!_dungeonHasLoaded) {
            _heartBeat--;
            if (_heartBeat <= 0) {
                if (Settings.get().clientVerbose) {
                    Logger.client("CLIENT: Heartbeating...");
                }
                sendMessage(Message.createHeartBeat());
                _heartBeat = 15;
            }
        }
    }

    public void dungeonHasLoaded() {
        Logger.client("CLIENT: Dungeon has finished loading...");
        _dungeonHasLoaded = true;
    }

    public boolean nextTurn() {
        update();
        if (_message != null) {
            if (_message.MessageType == MessageTypes.Sync_State) {
                if (Settings.get().clientVerbose) {
                    DevConsole.get().add("CLIENT: Synced:  " + _message.TurnCount + ". Seeding:  " + _message.RngSeed);
                }
                RNG.seed(_message.RngSeed);
                _heartBeat = 15;
            }
            return _message.MessageType == MessageTypes.Sync_State;
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
        return _initialPlayerIndex;
    }

    public void prepareForNextTurn() {
        sendMessage(Message.createReadyForNextTurn());
    }

    // Client <-> Server communication
    public boolean isActive(Command command, int playerIndex) {
        if (_playerStatus.containsKey(playerIndex) && _playerStatus.get(playerIndex).containsKey(command)) {
            return _playerStatus.get(playerIndex).get(command);
        }
        return false;
    }

    public void setState(Command command, int playerIndex, boolean isActive) {
        initPlayer(playerIndex, command);
        if (_playerStatus.get(playerIndex).get(command) != isActive) {
            if (Settings.get().clientVerbose) {
                Logger.client(String.format("CLIENT: Moves extends  CMD(%s) PI(%s) AC(%s)", command, playerIndex, isActive));
            }
            sendMessage(Message.createMovement(command, playerIndex, isActive));
        }
    }

    int _playerCount = 0;

    public int getPlayerCount() {
        if (_playerCount == 0) {
            sendMessage(Message.createPlayerCount(0));
            awaitReply(MessageTypes.Player_Count);
            _playerCount = _message.PlayerCount;
        }
        return _playerCount;
    }

    public void startGame() {
        sendMessage(Message.create(MessageTypes.Start_Game));
    }

    private void sendMessage(Message contents) {
        if (Settings.get().clientVerbose) {
            Logger.client("CLIENT: Sending message -> " + contents.MessageType);
        }
        contents.PlayerIndex = _initialPlayerIndex;
        contents.LocalPort = _comm.getLocalPort();
        _comm.sendOutboundMessage(contents);
    }

    // If the server doesn't reply at some point with the messageType you expect
    // Then the client will hang in an infinite loop.
    private void awaitReply(MessageTypes messageType) {
        if (Settings.get().clientVerbose) {
            Logger.client("CLIENT: Waiting for " + messageType);
        }
        while (true) {
            _message = _comm.readInboundMessage();
            if (_message != null) {
                if (_message.MessageType == messageType) {
                    if (Settings.get().clientVerbose) {
                        Logger.client("CLIENT: Right message received");
                    }
                    return;
                }
                else {
                    if (Settings.get().clientVerbose) {
                        Logger.client("CLIENT: Wrong message received:  " + _message.MessageType + "; Expected:  " + messageType);
                    }
                    handleResponse(_message);
                }
            }
        }
    }

    public void update() {
        _message = _comm.readInboundMessage();
        if (_message != null) {
            handleResponse(_message);
        }
    }

    private void handleResponse(Message contents) {
        switch (contents.MessageType) {
            case Connect:
                if (Settings.get().clientVerbose) {
                    Logger.client("CLIENT: Handshake successful. Starting player id extends  " + contents.PlayerIndex);
                }
                RNG.seed(contents.RngSeed);
                _initialPlayerIndex = (int) contents.PlayerCount;
                _isConnected = true;
                break;
            case Start_Game:
                Logger.client("CLIENT: Start game reply has been received");
                _isGameStarting = true;
                break;
            case Sync_State:
                if (Settings.get().clientVerbose) {
                    Logger.client("CLIENT: Input state received");
                }
                contents.readPlayerState(_playerStatus);
                break;
            default:
                if (Settings.get().clientVerbose) {
                    Logger.client("CLIENT: Unknown message type received -> " + _message.MessageType);
                }
                break;
        }
    }

    public void close() {
        Logger.client("CLIENT: Shutting down");
    }
}
