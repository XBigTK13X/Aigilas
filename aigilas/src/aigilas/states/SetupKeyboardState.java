package aigilas.states;

import aigilas.ui.UiAssets;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.io.Input;
import sps.io.InputBindings;
import sps.io.Keys;
import sps.states.StateManager;
import targets.DesktopGame;

import java.util.HashMap;
import java.util.Map;

public class SetupKeyboardState extends MenuState {
    final Label header;
    final Label command;
    final Label inUse;
    private int commandIndex = 0;
    private Command currentCommand;

    private Map<Integer, Integer> duplicateCatcher;

    private InputProcessor keyboardCatcher = new InputProcessor() {
        @Override
        public boolean keyDown(int i) {
            if (!duplicateCatcher.containsKey(i)) {
                currentCommand.bind(currentCommand.button(), Keys.find(i));
                selectNextCommand();
                duplicateCatcher.put(i, i);
                inUse.setVisible(false);
                return false;
            }
            inUse.setVisible(true);
            return false;
        }

        @Override
        public boolean keyUp(int i) {
            return false;
        }

        @Override
        public boolean keyTyped(char c) {
            return false;
        }

        @Override
        public boolean touchDown(int i, int i2, int i3, int i4) {
            return false;
        }

        @Override
        public boolean touchUp(int i, int i2, int i3, int i4) {
            return false;
        }

        @Override
        public boolean touchDragged(int i, int i2, int i3) {
            return false;
        }

        @Override
        public boolean mouseMoved(int i, int i2) {
            return false;
        }

        @Override
        public boolean scrolled(int i) {
            return false;
        }

        ;
    };

    private InputProcessor originalInputProcessor;


    public SetupKeyboardState() {
        header = new Label("Press the key for: ", UiAssets.getLabelStyle());
        command = new Label("", UiAssets.getLabelStyle());
        inUse = new Label("Already in use", UiAssets.getLabelStyle());
        duplicateCatcher = new HashMap<Integer, Integer>();
        inUse.setVisible(false);
        selectNextCommand();

        Input.disable();

        originalInputProcessor = DesktopGame.get().getInput().getInputProcessor();
        DesktopGame.get().getInput().setInputProcessor(keyboardCatcher);

        table.add(header);
        table.row();
        table.add(command);
        table.row();
        table.add(inUse);

    }

    private void selectNextCommand() {
        if (commandIndex < Commands.values().size()) {
            currentCommand = Commands.values().get(commandIndex);
            command.setText(currentCommand.name());
            commandIndex++;
        }
        else {
            InputBindings.persistCommandsToConfig();
            DesktopGame.get().getInput().setInputProcessor(originalInputProcessor);
            Input.enable();
            StateManager.loadState(new OptionsState());
        }
    }
}
