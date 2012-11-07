package sps.bridge;

import sps.core.Logger;

import java.util.HashMap;
import java.util.Map;

public class DrawDepths {

    private static DrawDepths instance;

    public static DrawDepth get(String name) {
        return instance.resolve(name);
    }

    public static void add(DrawDepth DrawDepth) {
        if (instance == null) {
            instance = new DrawDepths();
        }
        instance.put(DrawDepth);
    }

    private Map<String, DrawDepth> DrawDepths = new HashMap<String, DrawDepth>();

    private DrawDepths() {
    }

    public DrawDepth resolve(String name) {
        if (!DrawDepths.containsKey(name.toLowerCase())) {
            Logger.exception("The drawDepth " + name + " is not defined.", new Exception("Add it to bridge.cfg"));
        }
        return DrawDepths.get(name.toLowerCase());
    }

    public void put(DrawDepth DrawDepth) {
        DrawDepths.put(DrawDepth.Name, DrawDepth);
    }
}
