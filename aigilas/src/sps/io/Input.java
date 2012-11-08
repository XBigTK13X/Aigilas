package sps.io;

import aigilas.Common;
import com.badlogic.gdx.Gdx;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.bridge.Context;
import sps.bridge.Contexts;
import sps.core.Core;
import sps.net.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Input {
    // $$$ FIXME (Integer -> PlayerId) Maps a playerId to a context
    private static HashMap<Integer, Context> __contexts;

    // Lists what commands are locked for a given player
    private static final List<CommandLock> __locks = new ArrayList<CommandLock>();
    private static boolean __isInputActive = false;

    public static void setup() {
        __contexts = new HashMap<Integer, Context>();
        __contexts.put(0, Contexts.get(Common.Free));
        __contexts.put(1, Contexts.get(Common.Free));
        __contexts.put(2, Contexts.get(Common.Free));
        __contexts.put(3, Contexts.get(Common.Free));

        InputBindings.init();
    }

    public static boolean detectState(Command command, int playerIndex) {
        /*
           * boolean gamepadActive = GamePad.GetState(
           * PlayerIndex.values()[playerIndex]).IsButtonDown(
           * command.button());
           */
        // $$$
        boolean gamepadActive = false;
        return gamepadActive || (playerIndex == Client.get().getFirstPlayerIndex() && Gdx.input.isKeyPressed(command.key().getKeyCode()));
    }

    private static boolean isDown(Command command, int playerIndex) {
        return Client.get().isActive(command, playerIndex);
    }

    public static boolean isActive(Command command, int playerIndex) {
        return isActive(command, playerIndex, true);
    }

    public static boolean isActive(Command command, int playerIndex, boolean failIfLocked) {
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
    private static boolean shouldLock(Command command, int playerIndex) {
        return command.Context == __contexts.get(playerIndex) || (command.Context == Contexts.get(Core.Non_Free) && __contexts.get(playerIndex) != Contexts.get(Common.Free) || command.Context == Contexts.get(Core.All));
    }

    public static void setContext(Context context, int playerIndex) {
        __contexts.put(playerIndex, context);
    }

    public static boolean isContext(Context context, int playerIndex) {
        return __contexts.get(playerIndex) == context;
    }

    public static boolean isLocked(Command command, int playerIndex) {
        for (CommandLock pair : __locks) {
            if (pair.Command == command && pair.PlayerIndex == playerIndex) {
                return true;
            }
        }
        return false;
    }

    public static void lock(Command command, int playerIndex) {
        __locks.add(new CommandLock(command, playerIndex));
    }

    public static void unlock(Command command, int playerIndex) {
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

        for (Command command : Commands.values()) {
            Client.get().setState(command, Client.get().getFirstPlayerIndex(), detectState(command, Client.get().getFirstPlayerIndex()));
        }
    }
}
