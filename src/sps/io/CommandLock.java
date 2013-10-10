package sps.io;

import sps.bridge.Command;

public class CommandLock {
    public CommandLock(Command command, int playerIndex) {
        Command = command;
        PlayerIndex = playerIndex;
    }

    public final Command Command;
    public final int PlayerIndex;
}
