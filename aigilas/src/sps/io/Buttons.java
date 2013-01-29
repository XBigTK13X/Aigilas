package sps.io;

import sps.core.Logger;

public enum Buttons {
    LeftStickButton,
    LeftStickUp,
    LeftStickDown,
    LeftStickLeft,
    LeftStickRight,
    RightStickButton,
    RightStickUp,
    RightStickDown,
    RightStickLeft,
    RightStickRight,
    RightTrigger,
    LeftTrigger,
    RightShoulder,
    LeftShoulder,
    DPadUp,
    DPadLeft,
    DPadRight,
    DPadDown,
    Start,
    Back,
    A,
    X,
    Y,
    B;

    public static Buttons get(String s) {
        for (Buttons key : values()) {
            if (key.name().equalsIgnoreCase(s)) {
                return key;
            }
        }
        Logger.info("Unknown button being bound: " + s);
        return null;
    }
}
