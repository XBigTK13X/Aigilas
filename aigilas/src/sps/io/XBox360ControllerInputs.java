package sps.io;

import com.badlogic.gdx.controllers.PovDirection;
import org.apache.commons.lang3.SystemUtils;

public enum XBox360ControllerInputs {
    LeftStickButton(9, 8, 0),
    LeftStickUp(1, 0, 0, Device.Axis, false),
    LeftStickDown(1, 0, 0, Device.Axis, true),
    LeftStickLeft(0, 1, 0, Device.Axis, false),
    LeftStickRight(0, 1, 0, Device.Axis, true),
    RightStickButton(10, 9, 0),
    RightStickUp(4, 2, 0, Device.Axis, false),
    RightStickDown(4, 2, 0, Device.Axis, true),
    RightStickLeft(3, 3, 0, Device.Axis, false),
    RightStickRight(3, 3, 0, Device.Axis, true),
    RightTrigger(5, 4, 0),
    LeftTrigger(2, 4, 0),
    RightShoulder(5, 5, 0),
    LeftShoulder(4, 4, 0),
    DPadUp(0, 0, 0, PovDirection.north),
    DPadLeft(0, 0, 0, PovDirection.west),
    DPadRight(0, 0, 0, PovDirection.east),
    DPadDown(0, 0, 0, PovDirection.south),
    Start(7, 7, 0),
    Back(6, 6, 0),
    A(0, 0, 0),
    X(3, 2, 0),
    Y(2, 3, 0),
    B(1, 1, 0);

    final public int Index;
    final public ControllerInput Input;

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, int macIndex) {
        this(linuxIndex, windowsIndex, macIndex, Device.Button, null);
    }

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, int macIndex, PovDirection direction) {
        this(linuxIndex, windowsIndex, macIndex, Device.Pov, direction, null);
    }

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, int macIndex, Device device, Boolean positive) {
        this(linuxIndex, windowsIndex, macIndex, device, null, positive);
    }

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, int macIndex, Device device, PovDirection direction, Boolean positive) {
        if (SystemUtils.IS_OS_MAC) {
            Index = macIndex;
        }
        else if (SystemUtils.IS_OS_WINDOWS) {
            Index = windowsIndex;
        }
        else {
            Index = linuxIndex;
        }

        if (name().equalsIgnoreCase("RightTrigger")) {
            if (SystemUtils.IS_OS_WINDOWS) {
                Input = ControllerInput.createLessThanAxis(Index, ControllerAdapter.ZeroPoint);
            }
            else {
                Input = ControllerInput.createGreaterThanAxis(Index, ControllerAdapter.ZeroPoint);
                //return controller.getAxis(button.Index) > ControllerAdapter.ZeroPoint && leftShoulderInit;
            }
        }
        else if (name().equalsIgnoreCase("LeftTrigger")) {
            if (SystemUtils.IS_OS_WINDOWS) {
                Input = ControllerInput.createGreaterThanAxis(Index, -ControllerAdapter.ZeroPoint);
            }
            else {
                Input = ControllerInput.createGreaterThanAxis(Index, ControllerAdapter.ZeroPoint);
                //return controller.getAxis(button.Index) > ControllerAdapter.ZeroPoint && rightShoulderInit;
            }

        }
        else {
            switch (device) {
                case Button:
                    Input = ControllerInput.createButton(Index);
                    break;
                case Axis:
                    if (positive == null) {
                        Input = ControllerInput.createNonZeroAxis(Index);
                        break;
                    }
                    else if (positive) {
                        Input = ControllerInput.createPositiveAxis(Index);
                        break;
                    }
                    else {
                        Input = ControllerInput.createNegativeAxis(Index);
                        break;
                    }
                case Pov:
                    Input = ControllerInput.createPov(Index, direction);
                    break;
                default:
                    Input = null;
            }
        }
    }

    public static XBox360ControllerInputs get(String s) {
        for (XBox360ControllerInputs key : values()) {
            if (key.name().equalsIgnoreCase(s)) {
                return key;
            }
        }
        return null;
    }

    private static enum Device {
        Button,
        Axis,
        Pov
    }
}
