package sps.io;

public enum Buttons {

    LeftStickUp,
    LeftStickDown,
    LeftStickLeft,
    LeftStickRight,
    RightTrigger,
    DPadUp,
    X,
    Start,
    Back,
    RightShoulder,
    LeftShoulder,
    A,
    LeftTrigger,
    Y,
    B,
    DPadDown;

    public static Buttons get(String s) {
        for (Buttons key : values()) {
            if (key.name().equalsIgnoreCase(s)) {
                return key;
            }
        }
        return null;
    }
}
