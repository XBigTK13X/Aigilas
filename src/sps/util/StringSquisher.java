package sps.util;

public class StringSquisher {
    private static final StringBuilder builder = new StringBuilder(64);

    public static String build(String... textToSquish) {
        clear();
        for (String aTextToSquish : textToSquish) {
            builder.append(aTextToSquish);
        }
        return flush();
    }

    public static void squish(String... textToSquish) {
        for (String aTextToSquish : textToSquish) {
            builder.append(aTextToSquish);
        }
    }

    public static void clear() {
        builder.setLength(0);
    }

    public static String flush() {
        return builder.toString();
    }
}
