package sps.io;

import aigilas.management.Commands;
import com.badlogic.gdx.Gdx;
import sps.net.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Input {
    // $$$ FIXME (Integer -> PlayerId) Maps a playerId to a context
    private static HashMap<Integer, Contexts> __contexts;

    // Lists what commands are locked for a given player
    private static final List<CommandLock> __locks = new ArrayList<CommandLock>();

    // The commands that cannot be used by simply holding down the command's
    // input depending on the given context
    private static final HashMap<Commands, Contexts> __lockOnPress = new HashMap<Commands,Contexts>();
    private static final HashMap<Commands, Keys> _keyboardMapping = new HashMap<Commands,Keys>();
    private static final HashMap<Commands, Buttons> _gamePadMapping = new HashMap<Commands,Buttons>();
    private static boolean __isInputActive = false;

    public static void setup(IInputInitializer initializer) {
        __contexts = new HashMap<Integer,Contexts>();
        __contexts.put(0, Contexts.Free);
        __contexts.put(1, Contexts.Free);
        __contexts.put(2, Contexts.Free);
        __contexts.put(3, Contexts.Free);

        for (CommandDefinition command : initializer.getCommands()) {
            _keyboardMapping.put(command.Command, command.Keyboard);
            _gamePadMapping.put(command.Command, command.Gamepad);
            if (command.LockContext != null) {
                __lockOnPress.put(command.Command, command.LockContext);
            }
        }
    }

    public static boolean detectState(Commands command, int playerIndex) {
        /*
           * boolean gamepadActive = GamePad.GetState(
           * PlayerIndex.values()[playerIndex]).IsButtonDown(
           * _gamePadMapping.get(command));
           */
        // $$$
        boolean gamepadActive = false;
        return gamepadActive || (playerIndex == Client.get().getFirstPlayerIndex() && Gdx.input.isKeyPressed(_keyboardMapping.get(command).getKeyCode()));
    }

    private static boolean isDown(Commands command, int playerIndex) {
        return Client.get().isActive(command, playerIndex);
    }

    public static boolean isActive(Commands command, int playerIndex) {
        return isActive(command, playerIndex, true);
    }

    public static boolean isActive(Commands command, int playerIndex, boolean failIfLocked) {
        __isInputActive = isDown(command, playerIndex);
        if (!__isInputActive && shouldLock(command, playerIndex)) {
            unlock(command, playerIndex);
        }

        if (isLocked(command, playerIndex) && failIfLocked) {
            return false;
        }

        if (__isInputActive && shouldLock(command, playerIndex)) {
            lock(command, playerIndex);
        }

        return __isInputActive;
    }

    // If the key is marked to be locked on press and its lock context is
    // currently inactive
    private static boolean shouldLock(Commands command, int playerIndex) {
        for (Commands key : __lockOnPress.keySet()) {
            if (key == command) {
                if (__lockOnPress.get(key) == __contexts.get(playerIndex) || (__lockOnPress.get(key) == Contexts.Nonfree && __contexts.get(playerIndex) != Contexts.Free) || __lockOnPress.get(key) == Contexts.All) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void setContext(Contexts context, int playerIndex) {
        __contexts.put(playerIndex, context);
    }

    public static boolean isContext(Contexts context, int playerIndex) {
        return __contexts.get(playerIndex) == context;
    }

    public static boolean isLocked(Commands command, int playerIndex) {
        for (CommandLock pair : __locks) {
            if (pair.Command == command && pair.PlayerIndex == playerIndex) {
                return true;
            }
        }
        return false;
    }

    public static void lock(Commands command, int playerIndex) {
        __locks.add(new CommandLock(command, playerIndex));
    }

    public static void unlock(Commands command, int playerIndex) {
        for (int ii = 0; ii < __locks.size(); ii++) {
            if (__locks.get(ii).Command == command && __locks.get(ii).PlayerIndex == playerIndex) {
                __locks.remove(__locks.get(ii));
                ii--;
            }
        }
    }

    public static void update() {
        // Remove command locks if the associated key/button isn't being pressed
        for (int ii = 0; ii < __locks.size(); ii++) {
            if (!isDown(__locks.get(ii).Command, __locks.get(ii).PlayerIndex)) {
                __locks.remove(__locks.get(ii));
                ii--;
            }
        }

        for (Commands command : _keyboardMapping.keySet()) {
            Client.get().setState(command, Client.get().getFirstPlayerIndex(), detectState(command, Client.get().getFirstPlayerIndex()));
        }
    }
}
