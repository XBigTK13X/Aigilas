package sps.io;

import com.badlogic.gdx.Gdx;
import sps.bridge.Command;
import sps.bridge.Context;
import sps.bridge.Contexts;
import sps.bridge.Sps;
import sps.core.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Input {

    private static StateProvider stateProvider;

    // $$$ FIXME (Integer -> PlayerId) Maps a playerId to a context
    private static HashMap<Integer, Context> __contexts;

    // Lists what commands are locked for a given player
    private static final List<CommandLock> __locks = new ArrayList<CommandLock>();
    private static boolean __isInputActive = false;

    public static void setup(StateProvider stateProvider) {
        __contexts = new HashMap<Integer, Context>();
        __contexts.put(0, Contexts.get(Sps.Contexts.Free));
        __contexts.put(1, Contexts.get(Sps.Contexts.Free));
        __contexts.put(2, Contexts.get(Sps.Contexts.Free));
        __contexts.put(3, Contexts.get(Sps.Contexts.Free));

        if (stateProvider == null) {
            Input.stateProvider = new DefaultStateProvider();
        }
        else {
            Input.stateProvider = stateProvider;
        }

        InputBindings.init();
    }

    public static boolean detectState(Command command, int playerIndex) {
        boolean debugInput = false;
        boolean gamepadActive = XBox360Controller.get().isActive(command.button(), playerIndex);
        boolean keyboardActive = playerIndex == stateProvider.getFirstPlayerIndex() && Gdx.input.isKeyPressed(command.key().getKeyCode());
        if (debugInput && (gamepadActive || keyboardActive)) {
            Logger.info("ACTIVE: " + command.name());
        }
        return gamepadActive || keyboardActive;
    }

    private static boolean isDown(Command command, int playerIndex) {
        return stateProvider.isActive(command, playerIndex);
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
        return command.Context == __contexts.get(playerIndex) || (command.Context == Contexts.get(Sps.Contexts.Non_Free) && __contexts.get(playerIndex) != Contexts.get(Sps.Contexts.Free) || command.Context == Contexts.get(Sps.Contexts.All));
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

        stateProvider.pollLocalState();
    }
}
