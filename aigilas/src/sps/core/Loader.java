package sps.core;

import java.io.File;
import java.util.HashMap;

public class Loader {

    private static Loader instance;

    public static Loader get() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    private HashMap<String, File> files = new HashMap<String, File>();

    private String root = "assets";

    private File inputConfig = new File(root + "/data/input.cfg");

    public File inputConfig() {
        return inputConfig;
    }

    private File sprites = new File(root + "/graphics/sprites");

    public File sprites() {
        return sprites;
    }
}
