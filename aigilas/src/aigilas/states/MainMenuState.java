package aigilas.states;

import aigilas.management.Commands;
import sps.core.Logger;
import sps.core.Point2;
import sps.io.Contexts;
import sps.io.Input;
import sps.net.Client;
import sps.states.State;
import sps.states.StateManager;
import sps.text.ActionTextHandler;
import sps.text.StaticTextPool;

public class MainMenuState implements State {
    private final ActionTextHandler _text = new ActionTextHandler();

    private static final String PlayText = "Play Game";
    private static final String OptionsText = "Options";
    private static final String QuitText = "Quit Game";
    private static final String SelectionText = "--<";

    private boolean drawnOnce = false;
    private int _selection = 2;

    public MainMenuState() {
        Input.setContext(Contexts.Nonfree, Client.get().getFirstPlayerIndex());
    }

    @Override
    public void update() {
        int selectionVelocity = (Input.isActive(Commands.MoveUp, Client.get().getFirstPlayerIndex()) ? 1 : 0) + (Input.isActive(Commands.MoveDown, Client.get().getFirstPlayerIndex()) ? -1 : 0);
        _selection += selectionVelocity;
        _selection %= 3;
        if (_selection < 0) {
            _selection = 0;
        }

        if (Client.get().isGameStarting()) {
            for (int ii = 0; ii < Client.get().getPlayerCount(); ii++) {
                Input.setContext(Contexts.Free, ii);
            }

            StateManager.loadState(new LoadingState());
        }
        else {
            if (Input.isActive(Commands.Confirm, Client.get().getFirstPlayerIndex())) {
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
            StaticTextPool.get().clear();
            StaticTextPool.get().write(PlayText, new Point2(300, 300));
            StaticTextPool.get().write(OptionsText, new Point2(300, 200));
            StaticTextPool.get().write(QuitText, new Point2(300, 100));
            StaticTextPool.get().write(SelectionText, new Point2(225, 100 * (_selection + 1)));
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
        _text.draw();
        _text.clear();
    }
}
