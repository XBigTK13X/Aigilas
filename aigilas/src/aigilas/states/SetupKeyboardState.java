package aigilas.states;

import aigilas.Aigilas;
import aigilas.ui.UiAssets;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.io.Input;
import sps.io.Keys;
import sps.states.StateManager;
import targets.DesktopGame;

public class SetupKeyboardState extends MenuState {
    final Label header;
    final Label command;
    private int commandIndex = 0;
    private Command currentCommand;


    public SetupKeyboardState() {
        header = new Label("Press the key for: ", UiAssets.getLabelStyle());
        command = new Label("", UiAssets.getLabelStyle());
        selectNextCommand();

        Input.disable();

        DesktopGame.get().getInput().setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int i) {
                currentCommand.bind(currentCommand.button(),Keys.find(i));
                selectNextCommand();
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
        });

        table.add(header);
        table.row();
        table.add(command);

    }

    private void selectNextCommand() {
        if (commandIndex < Commands.values().size()) {
            currentCommand = Commands.values().get(commandIndex);
            command.setText(currentCommand.name());
            commandIndex++;
        }
        else{
            Input.enable();
            StateManager.loadState(new OptionsState());
        }
    }
}
