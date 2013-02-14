package aigilas.states;

import aigilas.ui.UiAssets;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.io.ControllerInput;
import sps.io.Input;
import sps.io.InputBindings;
import sps.states.StateManager;

import java.util.HashMap;
import java.util.Map;

public class SetupControllerState extends MenuState {
    final Label header;
    final Label command;
    final Label inUse;
    private int commandIndex = 0;
    private Command currentCommand;

    private Map<ControllerInput, ControllerInput> duplicateCatcher;

    private ControllerListener inputCatcher = new ControllerListener() {
        @Override
        public void connected(Controller controller) {
        }

        @Override
        public void disconnected(Controller controller) {
        }

        @Override
        public boolean buttonDown(Controller controller, int i) {
            bindInputToCurrentCommand(ControllerInput.createButton(i));
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
            if (povDirection != PovDirection.center) {
                bindInputToCurrentCommand(ControllerInput.createPov(i, povDirection));
            }
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
        duplicateCatcher = new HashMap<ControllerInput, ControllerInput>();
        inUse.setVisible(false);
        selectNextCommand();

        Input.disable();

        Controllers.addListener(inputCatcher);

        table.add(header);
        table.row();
        table.add(command);
        table.row();
        table.add(inUse);
    }

    private void bindInputToCurrentCommand(ControllerInput input) {
        if (!duplicateCatcher.containsKey(input)) {
            currentCommand.bind(input, currentCommand.key());
            selectNextCommand();
            duplicateCatcher.put(input, input);
            inUse.setVisible(false);
        }
        inUse.setVisible(true);
    }

    private void selectNextCommand() {
        if (commandIndex < Commands.values().size()) {
            currentCommand = Commands.values().get(commandIndex);
            command.setText(currentCommand.name());
            commandIndex++;
        }
        else {
            InputBindings.persistCommandsToConfig();
            Controllers.removeListener(inputCatcher);
            Input.enable();
            StateManager.loadState(new OptionsState());
        }
    }
}
