package spx.io;

import aigilas.management.Commands;

public class CommandDefinition {
    public final Commands Command;
    public final Buttons Gamepad;
    public final Keys Keyboard;
    public final Contexts LockContext;

    public CommandDefinition(Commands command, Keys key, Buttons button, Contexts lockContext) {
        Command = command;
        Gamepad = button;
        Keyboard = key;
        LockContext = lockContext;
    }
}
