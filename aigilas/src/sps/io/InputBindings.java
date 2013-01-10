package sps.io;

import sps.bridge.Commands;
import sps.core.Loader;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class InputBindings {
    public static InputBindings __instance;

    public static void init() {
        __instance = new InputBindings();
    }

    public InputBindings() {
        try {
            FileInputStream fstream = new FileInputStream(Loader.get().inputConfig());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            Logger.info("Parsing input.cfg");
            while ((line = br.readLine()) != null) {
                if (!line.contains("##") && line.length() > 1) {
                    String key = line.split(",")[0];
                    String value = line.split(",")[1];
                    Keys keyBinding = Keys.get(value.split("-")[0]);
                    Buttons buttonBinding = Buttons.get(value.split("-")[1]);
                    Commands.get(key).bind(buttonBinding, keyBinding);
                }
            }
            in.close();
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

}
