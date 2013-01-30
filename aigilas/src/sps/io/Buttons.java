package sps.io;

import org.apache.commons.lang3.SystemUtils;

public enum Buttons {
    LeftStickButton(9, 8, 0),
    LeftStickUp(1, 0, 0),
    LeftStickDown(1, 0, 0),
    LeftStickLeft(0, 1, 0),
    LeftStickRight(0, 1, 0),
    RightStickButton(10, 9, 0),
    RightStickUp(4, 2, 0),
    RightStickDown(4, 2, 0),
    RightStickLeft(3, 3, 0),
    RightStickRight(3, 3, 0),
    RightTrigger(5, 4, 0),
    LeftTrigger(2, 4, 0),
    RightShoulder(5, 5, 0),
    LeftShoulder(4, 4, 0),
    DPadUp(0, 0, 0),
    DPadLeft(0, 0, 0),
    DPadRight(0, 0, 0),
    DPadDown(0, 0, 0),
    Start(7, 7, 0),
    Back(6, 6, 0),
    A(0, 0, 0),
    X(3, 2, 0),
    Y(2, 3, 0),
    B(1, 1, 0);

    final public int Index;

    private Buttons(int lindex, int windex, int mindex) {
        if (SystemUtils.IS_OS_MAC) {
            Index = mindex;
        } else if (SystemUtils.IS_OS_WINDOWS) {
            Index = windex;
        } else {
            Index = lindex;
        }
    }

    public static Buttons get(String s) {
        for (Buttons key : values()) {
            if (key.name().equalsIgnoreCase(s)) {
                return key;
            }
        }
        return null;
    }
}
