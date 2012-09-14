package spx.util;

import java.util.HashMap;

public class StringStorage {
	private static HashMap<Integer, String> __slots = new HashMap<Integer, String>();

	public static String Get(float value) {
		if (__slots.keySet().size() == 0) {
			for (int ii = 0; ii < 1000; ii++) {
				__slots.put((int) ii, Integer.toString(ii));
			}
		}
		if (__slots.containsKey((int) value)) {
			return __slots.get((int) value);
		}
		return Float.toString(value);
	}
}