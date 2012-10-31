package aigilas.states;

import aigilas.energygement.Commands;
import sps.core.Logger;
import sps.io.Contexts;
import sps.io.Input;
import sps.net.Client;
import sps.states.State;
import sps.states.StateManager;
import sps.text.ActionTextHandler;

public class MainMenuState implements State {
    private final ActionTextHandler _text = new ActionTextHandler();

    private static final String PlayText = "Play Game";
    private static final String OptionsText = "Options";
    private static final String QuitText = "Quit Game";
    private static final String SelectionText = "--<";

    private int _selection = 2;

    public MainMenuState() {
        Input.setContext(Contexts.Nonfree, Client.get().getFirstPlayerIndex());
    }

    @Override
    public void update() {
        _text.writeAction(PlayText, 1, 300, 300);
        _text.writeAction(OptionsText, 1, 300, 200);
        _text.writeAction(QuitText, 1, 300, 100);

        _selection += (Input.isActive(Commands.MoveUp, Client.get().getFirstPlayerIndex()) ? 1 : 0) + (Input.isActive(Commands.MoveDown, Client.get().getFirstPlayerIndex()) ? -1 : 0);
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

        _text.writeAction(SelectionText, 1, 225, 100 * (_selection + 1));
    }

    @Override
    public void loadContent() {

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
