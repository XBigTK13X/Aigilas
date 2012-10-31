package sps.net;

import aigilas.energygement.Commands;
import sps.core.Logger;
import sps.core.Settings;

import java.util.HashMap;

public class Server extends Thread {
    private boolean isRunning = true;
    private final HashMap<Integer, HashMap<Commands, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Commands, Boolean>>();
    private final int _rngSeed = (int) System.currentTimeMillis();
    private Message _message = Message.empty();
    private Integer _turnCount = 0;
    private final boolean[] _readyCheckIn = {true, true, true, true};

    private final ClientManager clients;

    public Server() {
        setName("Server");
        clients = new ClientManager();
        for (int ii = 0; ii < Message.PlayerMax; ii++) {
            _playerStatus.put(ii, new HashMap<Commands, Boolean>());
            for (Commands command : Commands.values()) {
                _playerStatus.get(ii).put(command, false);
            }
        }
    }

    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            pollForNewMessages();
            broadCastGameState();
        }
    }

    private void pollForNewMessages() {
        _message = clients.readMessage();
        if (_message != null) {
            if (Settings.get().serverVerbose) {
                Logger.server("SERVER: Message received: " + _message.MessageType);
            }
            switch (_message.MessageType) {
                case Connect:
                    Logger.server("SERVER: New client connection");
                    sendMessage(Message.createInit(clients.size() - 1, _rngSeed), _message.LocalPort);
                    if (Settings.get().serverVerbose) {
                        Logger.server("SERVER: Accepted new connection");
                    }
                    _turnCount = 0;
                    break;
                case Check_State:
                    initPlayer(_message.PlayerIndex, _message.Command);
                    _message.IsActive = _playerStatus.get(_message.PlayerIndex).get(_message.Command);
                    if (Settings.get().serverVerbose) {
                        Logger.server(String.format("SERVER: Check extends  CMD(%s) PI(%s) AC(%s)", _message.PlayerIndex, _message.Command, _playerStatus.get(_message.PlayerIndex).get(_message.Command)));
                    }
                    sendMessage(_message, _message.LocalPort);
                    break;

                case Movement:
                    initPlayer(_message.PlayerIndex, _message.Command);
                    if (Settings.get().serverVerbose) {
                        Logger.server(String.format("SERVER: Moves:  CMD(%s) PI(%s) AC(%s)", _message.PlayerIndex, _message.Command, _message.IsActive));
                    }
                    _playerStatus.get(_message.PlayerIndex).put(_message.Command, _message.IsActive);
                    break;

                case Start_Game:
                    Logger.server("SERVER: Announcing game commencement.");
                    announce(_message);
                    break;

                case Player_Count:
                    if (Settings.get().serverVerbose) {
                        Logger.server("SERVER: PLAYER COUNT");
                    }
                    sendMessage(Message.createPlayerCount(clients.size()), _message.LocalPort);
                    break;

                case Ready_For_Next_Turn:
                    if (Settings.get().serverVerbose) {
                        Logger.server("SERVER: Received ready signal from client");
                    }
                    _readyCheckIn[_message.PlayerIndex] = true;
                    break;

                case Heart_Beat:
                    _readyCheckIn[_message.PlayerIndex] = true;
                    break;
                default:
                    if (Settings.get().serverVerbose) {
                        Logger.server("SERVER: Unknown message");
                    }
                    break;
            }
        }
    }

    private void initPlayer(int playerIndex, Commands command) {
        if (!_playerStatus.containsKey(playerIndex)) {
            _playerStatus.put(playerIndex, new HashMap<Commands, Boolean>());
        }
        if (!_playerStatus.get(playerIndex).containsKey(command)) {
            _playerStatus.get(playerIndex).put(command, false);
        }
    }

    public boolean isOnlyInstance() {
        return clients.isOnlyInstance();
    }

    private void broadCastGameState() {
        int readyCount = 0;
        for (boolean a_readyCheckIn : _readyCheckIn) {
            readyCount += a_readyCheckIn ? 1 : 0;
        }
        if (readyCount >= clients.size()) {
            if (Settings.get().serverVerbose) {
                Logger.server("SERVER: Announcing player input status.");
            }
            announce(Message.createPlayerState(_playerStatus, _turnCount++, _rngSeed));
            for (int ii = 0; ii < _readyCheckIn.length; ii++) {
                _readyCheckIn[ii] = false;
            }
        }
    }

    private void announce(Message contents) {
        clients.announce(contents);
    }

    private void sendMessage(Message contents, int localPort) {
        contents.LocalPort = localPort;
        clients.send(contents);
    }

    public void close() {
        isRunning = false;
        Logger.server("SERVER: Shutting down");
    }
}
