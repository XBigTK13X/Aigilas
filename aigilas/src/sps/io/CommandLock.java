package sps.io;

import aigilas.energygement.Commands;

public class CommandLock {
    public CommandLock(Commands command, int playerIndex) {
        Command = command;
        PlayerIndex = playerIndex;
    }

    public final Commands Command;
    public final int PlayerIndex;
}
