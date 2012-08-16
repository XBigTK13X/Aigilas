package com.spx.io;

import com.xna.wrapper.Buttons;
import com.xna.wrapper.keySet;

public class CommandDefinition
{
    public int Command;
    public Buttons Gamepad;
    public Keys Keyboard;
    public int LockContext ;
	
    public CommandDefinition(int command, Keys key, Buttons button, int lockContext)
    {
        Command = command;
        Gamepad = button;
        Keyboard = key;
        LockContext = lockContext;
    }
}