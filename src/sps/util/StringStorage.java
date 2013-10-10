package sps.util;

import java.util.HashMap;

public class StringStorage {
    private static final HashMap<Integer, String> __slots = new HashMap<Integer, String>();

    public static String get(float value) {
        if (__slots.keySet().size() == 0) {
            for (int ii = 0; ii < 10000; ii++) {
                __slots.put(ii, Integer.toString(ii));
            }
        }
        if (__slots.containsKey((int) value)) {
            return __slots.get((int) value);
        }
        return Float.toString(value);
    }
}
