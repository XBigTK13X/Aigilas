package sps.io;

import com.badlogic.gdx.controllers.PovDirection;
import org.apache.commons.lang3.SystemUtils;

public enum XBox360ControllerInputs {
    LeftStickButton(9, 8),
    LeftStickUp(1, 0, Device.Axis, false),
    LeftStickDown(1, 0, Device.Axis, true),
    LeftStickLeft(0, 1, Device.Axis, false),
    LeftStickRight(0, 1, Device.Axis, true),
    RightStickButton(10, 9),
    RightStickUp(4, 2, Device.Axis, false),
    RightStickDown(4, 2, Device.Axis, true),
    RightStickLeft(3, 3, Device.Axis, false),
    RightStickRight(3, 3, Device.Axis, true),
    RightTrigger(5, 4),
    LeftTrigger(2, 4),
    RightShoulder(5, 5),
    LeftShoulder(4, 4),
    DPadUp(0, 0, PovDirection.north),
    DPadLeft(0, 0, PovDirection.west),
    DPadRight(0, 0, PovDirection.east),
    DPadDown(0, 0, PovDirection.south),
    Start(7, 7),
    Back(6, 6),
    A(0, 0),
    X(3, 2),
    Y(2, 3),
    B(1, 1);

    final public int Index;
    final public ControllerInput Input;

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex) {
        this(linuxIndex, windowsIndex, Device.Button, null);
    }

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, PovDirection direction) {
        this(linuxIndex, windowsIndex, Device.Pov, direction, null);
    }

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, Device device, Boolean positive) {
        this(linuxIndex, windowsIndex, device, null, positive);
    }

    private XBox360ControllerInputs(int linuxIndex, int windowsIndex, Device device, PovDirection direction, Boolean positive) {
        if (SystemUtils.IS_OS_MAC) {
            Input = MacOsXXboxMappings.inputs.get(name());
            Index = 0;
        }
        else {
            if (SystemUtils.IS_OS_WINDOWS) {
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
                }
            }
            else if (name().equalsIgnoreCase("LeftTrigger")) {
                if (SystemUtils.IS_OS_WINDOWS) {
                    Input = ControllerInput.createGreaterThanAxis(Index, -ControllerAdapter.ZeroPoint);
                }
                else {
                    Input = ControllerInput.createGreaterThanAxis(Index, ControllerAdapter.ZeroPoint);
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
