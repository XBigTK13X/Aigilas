package sps.bridge;

import sps.core.Logger;

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
        if (!contexts.containsKey(name.toLowerCase())) {
            Logger.exception("The context " + name + " is not defined.", new Exception("Add it to contexts.cfg"));
        }
        return contexts.get(name.toLowerCase());
    }

    public void put(Context context) {
        contexts.put(context.Name, context);
    }
}
