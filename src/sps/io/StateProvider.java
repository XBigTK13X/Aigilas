package sps.io;

import sps.bridge.Command;

public interface StateProvider {
    public boolean isActive(Command command, int playerIndex);

    public int getFirstPlayerIndex();

    public void setState(Command command, int playerIndex, boolean isActive);

    public void pollLocalState();
}
