package sps.bridge;

import java.util.HashMap;
import java.util.Map;

public class Contexts {

    private static Contexts instance;

    public static Context get(String name) {
        return instance.resolve(name);
    }

    public static void add(Context context) {
        if (instance == null) {
            instance = new Contexts();
        }
        instance.put(context);
    }

    private Map<String, Context> contexts = new HashMap<String, Context>();

    private Contexts() {
    }

    public Context resolve(String name) {
        return contexts.get(name);
    }

    public void put(Context context) {
        contexts.put(context.Name, context);
    }
}
