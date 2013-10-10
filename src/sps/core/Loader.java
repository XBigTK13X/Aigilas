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

    private String graphics = "graphics";
    private String data = "data";
    private String music = "music";

    private File get(String dir, String target) {
        return new File(root + "/" + dir + "/" + target);
    }

    public File data(String target) {
        return get(data, target);
    }

    public File graphics(String target) {
        return get(graphics, target);
    }

    public File music(String target) {
        return get(music, target);
    }
}
