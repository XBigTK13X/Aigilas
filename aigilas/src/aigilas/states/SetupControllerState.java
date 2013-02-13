package aigilas.states;

import aigilas.ui.UiAssets;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
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

public class SetupControllerState extends MenuState {
    final Label header;
    final Label command;
    final Label inUse;
    private int commandIndex = 0;
    private Command currentCommand;

    private Map<Integer, Integer> duplicateCatcher;

    private ControllerListener inputCatcher = new ControllerListener() {
        @Override
        public void connected(Controller controller) {
        }

        @Override
        public void disconnected(Controller controller) {
        }

        @Override
        public boolean buttonDown(Controller controller, int i) {
            return false;
        }

        @Override
        public boolean buttonUp(Controller controller, int i) {
            return false;
        }

        @Override
        public boolean axisMoved(Controller controller, int axisIndex, float value) {

            return false;
        }

        @Override
        public boolean povMoved(Controller controller, int i, PovDirection povDirection) {
            return false;
        }

        @Override
        public boolean xSliderMoved(Controller controller, int i, boolean b) {
            return false;
        }

        @Override
        public boolean ySliderMoved(Controller controller, int i, boolean b) {
            return false;
        }

        @Override
        public boolean accelerometerMoved(Controller controller, int i, Vector3 vector3) {
            return false;
        }
    };

    public SetupControllerState() {
        header = new Label("Press the button for: ", UiAssets.getLabelStyle());
        command = new Label("", UiAssets.getLabelStyle());
        inUse = new Label("Already in use", UiAssets.getLabelStyle());
        duplicateCatcher = new HashMap<Integer, Integer>();
        inUse.setVisible(false);
        selectNextCommand();

        Input.disable();

        DesktopGame.get().getInput().setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int i) {
                if (!duplicateCatcher.containsKey(i)) {
                    currentCommand.bind(currentCommand.controllerInput(), Keys.find(i));
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
        });

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
            Input.enable();
            StateManager.loadState(new OptionsState());
        }
    }
}
