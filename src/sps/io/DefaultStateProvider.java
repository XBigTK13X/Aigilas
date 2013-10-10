package sps.io;

import sps.bridge.Command;

public class DefaultStateProvider implements StateProvider {
    private final CommandState state = new CommandState();

    @Override
    public boolean isActive(Command command, int playerIndex) {
        return state.isActive(playerIndex, command);
    }

    @Override
    public int getFirstPlayerIndex() {
        return 0;
    }

    @Override
    public void setState(Command command, int playerIndex, boolean isActive) {
        state.setState(playerIndex, command, isActive);
    }

    @Override
    public void pollLocalState() {

    }
}
