package sps.io;

import aigilas.energygement.Commands;
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
    private static boolean __isInputActive = false;

    public static void setup() {
        __contexts = new HashMap<Integer, Contexts>();
        __contexts.put(0, Contexts.Free);
        __contexts.put(1, Contexts.Free);
        __contexts.put(2, Contexts.Free);
        __contexts.put(3, Contexts.Free);

        InputBindings.init();
    }

    public static boolean detectState(Commands command, int playerIndex) {
        /*
           * boolean gamepadActive = GamePad.GetState(
           * PlayerIndex.values()[playerIndex]).IsButtonDown(
           * command.button());
           */
        // $$$
        boolean gamepadActive = false;
        try {
            return gamepadActive || (playerIndex == Client.get().getFirstPlayerIndex() && Gdx.input.isKeyPressed(command.key().getKeyCode()));
        }
        catch (Exception e) {
            int x = 0;
        }
        return false;
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
        return command.Context == __contexts.get(playerIndex) || (command.Context == Contexts.Nonfree && __contexts.get(playerIndex) != Contexts.Free) || command.Context == Contexts.All;
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

        for (Commands command : Commands.values()) {
            Client.get().setState(command, Client.get().getFirstPlayerIndex(), detectState(command, Client.get().getFirstPlayerIndex()));
        }
    }
}
