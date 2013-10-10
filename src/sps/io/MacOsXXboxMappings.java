package sps.io;

import java.util.HashMap;
import java.util.Map;

public class MacOsXXboxMappings {
    public static final Map<String, ControllerInput> inputs = new HashMap<String, ControllerInput>();

    static {
        inputs.put("A", ControllerInput.createButton(11));
        inputs.put("B", ControllerInput.createButton(12));
        inputs.put("X", ControllerInput.createButton(13));
        inputs.put("Y", ControllerInput.createButton(14));
        inputs.put("DPadUp", ControllerInput.createButton(0));
        inputs.put("DPadLeft", ControllerInput.createButton(2));
        inputs.put("DPadDown", ControllerInput.createButton(1));
        inputs.put("DpadRight", ControllerInput.createButton(3));
        inputs.put("Start", ControllerInput.createButton(4));
        inputs.put("Back", ControllerInput.createButton(5));
        inputs.put("LeftShoulder", ControllerInput.createButton(8));
        inputs.put("RightShoulder", ControllerInput.createButton(9));
        inputs.put("LeftStickButton", ControllerInput.createButton(6));
        inputs.put("RightStickButton", ControllerInput.createButton(7));
        inputs.put("LeftStickUp", ControllerInput.createNegativeAxis(3));
        inputs.put("LeftStickDown", ControllerInput.createPositiveAxis(3));
        inputs.put("LeftStickLeft", ControllerInput.createNegativeAxis(2));
        inputs.put("LeftStickRight", ControllerInput.createPositiveAxis(2));
        inputs.put("RightStickUp", ControllerInput.createNegativeAxis(5));
        inputs.put("RightStickDown", ControllerInput.createPositiveAxis(5));
        inputs.put("RightStickLeft", ControllerInput.createNegativeAxis(4));
        inputs.put("RightStickRight", ControllerInput.createPositiveAxis(4));
        inputs.put("RightTrigger", ControllerInput.createPositiveAxis(1));
        inputs.put("LeftTrigger", ControllerInput.createPositiveAxis(0));
    }
}
