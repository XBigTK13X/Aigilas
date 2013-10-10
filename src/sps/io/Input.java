package sps.io;

import aigilas.Aigilas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import sps.bridge.Command;
import sps.bridge.Context;
import sps.bridge.Contexts;
import sps.bridge.Sps;
import sps.core.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Input implements InputProvider {

    private static InputProvider instance = new Input();
    private static InputProvider falseInstance = new FalseInput();
    private static boolean disabled = false;
    // Lists what commands are locked for a given player
    private final List<CommandLock> __locks = new ArrayList<CommandLock>();
    private StateProvider provider;
    // $$$ FIXME (Integer -> PlayerId) Maps a playerId to a context
    private HashMap<Integer, Context> __contexts;
    private boolean __isInputActive = false;

    public static InputProvider get() {
        return isDisabled() ? falseInstance : instance;
    }

    public static void enable() {
        disabled = false;
    }

    public static void disable() {
        disabled = true;
    }

    public static boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setup(StateProvider stateProvider) {
        __contexts = new HashMap<Integer, Context>();
        __contexts.put(0, Contexts.get(Sps.Contexts.Free));
        __contexts.put(1, Contexts.get(Sps.Contexts.Free));
        __contexts.put(2, Contexts.get(Sps.Contexts.Free));
        __contexts.put(3, Contexts.get(Sps.Contexts.Free));

        if (stateProvider == null) {
            provider = new DefaultStateProvider();
        }
        else {
            provider = stateProvider;
        }

        InputBindings.init();
    }

    @Override
    public boolean detectState(Command command, int playerIndex) {
        boolean debugInput = false;
        boolean gamepadActive = false;
        if (Controllers.getControllers().size > playerIndex) {
            gamepadActive = command.controllerInput().isActive(Controllers.getControllers().get(playerIndex));
        }
        boolean keyboardActive = (playerIndex == provider.getFirstPlayerIndex()) && Gdx.input.isKeyPressed(command.key().getKeyCode());

        if (debugInput && command.equals(Aigilas.Commands.MoveLeft)) {
            if (debugInput && (gamepadActive || keyboardActive)) {
                Logger.info("ACTIVE: " + command.name() + ", controller:" + gamepadActive + ", keyboard:" + keyboardActive);
            }
            else {
                Logger.info("INACTIVE: " + command.name() + ", controller:" + gamepadActive + ", keyboard:" + keyboardActive);
            }
        }

        return gamepadActive || keyboardActive;
    }

    private boolean isDown(Command command, int playerIndex) {
        return provider.isActive(command, playerIndex);
    }

    public boolean isActive(Command command, int playerIndex) {
        return isActive(command, playerIndex, true);
    }

    @Override
    public boolean isActive(Command command, int playerIndex, boolean failIfLocked) {
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
    private boolean shouldLock(Command command, int playerIndex) {
        return command.Context == __contexts.get(playerIndex) || (command.Context == Contexts.get(Sps.Contexts.Non_Free) && __contexts.get(playerIndex) != Contexts.get(Sps.Contexts.Free) || command.Context == Contexts.get(Sps.Contexts.All));
    }

    @Override
    public void setContext(Context context, int playerIndex) {
        __contexts.put(playerIndex, context);
    }

    @Override
    public boolean isContext(Context context, int playerIndex) {
        return __contexts.get(playerIndex) == context;
    }

    @Override
    public boolean isLocked(Command command, int playerIndex) {
        for (CommandLock pair : __locks) {
            if (pair.Command == command && pair.PlayerIndex == playerIndex) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void lock(Command command, int playerIndex) {
        __locks.add(new CommandLock(command, playerIndex));
    }

    @Override
    public void unlock(Command command, int playerIndex) {
        for (int ii = 0; ii < __locks.size(); ii++) {
            if (__locks.get(ii).Command == command && __locks.get(ii).PlayerIndex == playerIndex) {
                __locks.remove(__locks.get(ii));
                ii--;
            }
        }
    }

    @Override
    public void update() {
        // Remove command locks if the associated key/button isn't being pressed
        for (int ii = 0; ii < __locks.size(); ii++) {
            if (!isDown(__locks.get(ii).Command, __locks.get(ii).PlayerIndex)) {
                __locks.remove(__locks.get(ii));
                ii--;
            }
        }

        provider.pollLocalState();
    }
}
