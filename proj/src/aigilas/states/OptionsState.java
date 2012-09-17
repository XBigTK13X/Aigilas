package aigilas.states;

import aigilas.management.Commands;
import spx.io.Contexts;
import spx.io.Input;
import spx.net.Client;
import spx.states.State;
import spx.states.StateManager;
import spx.text.ActionTextHandler;

public class OptionsState implements State {
    private ActionTextHandler _text = new ActionTextHandler();

    private static final String PlayText = "Play Game";
    private static final String OptionsText = "Options";
    private static final String QuitText = "Quit Game";
    private static final String SelectionText = "--<";

    private int _selection;

    public OptionsState() {
        Input.setContext(Contexts.Nonfree, Client.get().getFirstPlayerIndex());
    }

    @Override
    public void update() {
        _text.writeAction(PlayText, 1, 300, 100);
        _text.writeAction(OptionsText, 1, 300, 200);
        _text.writeAction(QuitText, 1, 300, 300);

        _selection += (Input.isActive(Commands.MoveDown, 0) ? 1 : 0) + (Input.isActive(Commands.MoveUp, 0) ? -1 : 0);
        _selection %= 3;

        if (Input.isActive(Commands.Confirm, Client.get().getFirstPlayerIndex())) {
            switch (_selection) {
                case 0:
                    Input.setContext(Contexts.Free, Client.get().getFirstPlayerIndex());
                    StateManager.loadState(new GameplayState());
                    return;
                case 1:
                    StateManager.loadState(new OptionsState());
                    return;
                case 2:
                    System.exit(0);
                    return;
                default:
                    break;
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
