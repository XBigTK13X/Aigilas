package spx.util;

import java.util.HashMap;

public class IntegerStorage {
    private static final HashMap<Float, Integer> __slots = new HashMap<>();

    public static int get(float value) {
        if (!__slots.containsKey(value)) {
            __slots.put(value, (int) value);
        }
        return __slots.get(value);
    }
}
