package sps.io;

import sps.bridge.Command;
import sps.bridge.Commands;

import java.util.HashMap;

public class CommandState {
    private final HashMap<Integer, HashMap<Command, Boolean>> _state = new HashMap<Integer, HashMap<Command, Boolean>>();
    private final int MaxPlayers = 4;

    public CommandState() {
        for (int ii = 0; ii < MaxPlayers; ii++) {
            _state.put(ii, new HashMap<Command, Boolean>());
            for (Command command : Commands.values()) {
                _state.get(ii).put(command, false);
            }
        }
    }

    public boolean isActive(int player, Command command) {
        initPlayer(player, command);
        return _state.get(player).get(command);
    }

    public void setState(int player, Command command, boolean isActive) {
        initPlayer(player, command);
        _state.get(player).put(command, isActive);
    }

    private void initPlayer(int playerIndex, Command command) {
        if (!_state.containsKey(playerIndex)) {
            _state.put(playerIndex, new HashMap<Command, Boolean>());
        }
        if (!_state.get(playerIndex).containsKey(command)) {
            _state.get(playerIndex).put(command, false);
        }
    }

    public void reset(CommandState cs) {
        for (int ii = 0; ii < MaxPlayers; ii++) {
            if (cs._state.containsKey(ii)) {
                for (Command command : Commands.values()) {
                    if (cs._state.get(ii).containsKey(command)) {
                        setState(ii, command, cs._state.get(ii).get(command));
                    }
                }
            }
        }
    }

    public String debug() {
        String result = "";
        for (Integer key : _state.keySet()) {
            result += "{PI:" + key + ",";
            HashMap<Command, Boolean> upOrDowns = _state.get(key);
            for (Command command : upOrDowns.keySet()) {
                result += "{c:" + command.name() + ",?: " + upOrDowns.get(command) + "}";
            }
            result += "}";
        }
        return result;
    }
}
