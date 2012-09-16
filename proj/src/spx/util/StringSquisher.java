package spx.util;

public class StringSquisher {
    private static StringBuilder builder = new StringBuilder(64);

    public static String build(String... textToSquish) {
        clear();
        for (int ii = 0; ii < textToSquish.length; ii++) {
            builder.append(textToSquish[ii]);
        }
        return flush();
    }

    public static void squish(String... textToSquish) {
        for (int ii = 0; ii < textToSquish.length; ii++) {
            builder.append(textToSquish[ii]);
        }
    }

    public static void clear() {
        builder.setLength(0);
    }

    public static String flush() {
        return builder.toString();
    }
}
