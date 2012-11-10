package aigilas.states;

import aigilas.Common;
import sps.bridge.Commands;
import sps.bridge.Contexts;
import sps.core.Core;
import sps.core.Logger;
import sps.core.Point2;
import sps.io.Input;
import aigilas.net.Client;
import sps.states.State;
import sps.states.StateManager;
import sps.text.TextPool;

public class MainMenuState implements State {

    private static final String PlayText = "Play Game";
    private static final String OptionsText = "Options";
    private static final String QuitText = "Quit Game";
    private static final String SelectionText = "--<";

    private boolean drawnOnce = false;
    private int _selection = 2;

    public MainMenuState() {
        Input.setContext(Contexts.get(Core.Non_Free), Client.get().getFirstPlayerIndex());
    }

    @Override
    public void update() {
        int selectionVelocity = (Input.isActive(Commands.get(Common.MoveUp), Client.get().getFirstPlayerIndex()) ? 1 : 0) + (Input.isActive(Commands.get(Common.MoveDown), Client.get().getFirstPlayerIndex()) ? -1 : 0);
        _selection += selectionVelocity;
        _selection %= 3;
        if (_selection < 0) {
            _selection = 0;
        }

        if (Client.get().isGameStarting()) {
            for (int ii = 0; ii < Client.get().getPlayerCount(); ii++) {
                Input.setContext(Contexts.get(Common.Free), ii);
            }

            StateManager.loadState(new LoadingState());
        }
        else {
            if (Input.isActive(Commands.get(Common.Confirm), Client.get().getFirstPlayerIndex())) {
                switch (_selection) {
                    case 2:
                        Logger.info("Starting the game");
                        Client.get().startGame();
                        return;
                    case 1:
                        //$$$ StateManager.loadState(new OptionsState());
                        return;
                    case 0:
                        System.exit(0);
                        return;
                    default:
                        break;
                }
            }
        }

        if (selectionVelocity != 0 || !drawnOnce) {
            drawnOnce = true;
            TextPool.get().clear();
            TextPool.get().write(PlayText, new Point2(300, 300));
            TextPool.get().write(OptionsText, new Point2(300, 200));
            TextPool.get().write(QuitText, new Point2(300, 100));
            TextPool.get().write(SelectionText, new Point2(225, 100 * (_selection + 1)));
        }
    }

    @Override
    public void load() {

    }

    @Override
    public void unload() {

    }

    @Override
    public void draw() {
    }
}
