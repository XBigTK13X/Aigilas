package sps.io;

import org.apache.commons.io.FileUtils;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.core.Loader;
import sps.core.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputBindings {
    public static InputBindings __instance;

    public static void init() {
        __instance = new InputBindings();
    }

    public InputBindings() {
        try {
            Logger.info("Parsing input.cfg");
            for (String line : FileUtils.readLines(Loader.get().data("input.cfg"))) {
                if (!line.contains("##") && line.length() > 1) {
                    String key = line.split(",")[0];
                    String value = line.split(",")[1];
                    Keys keyBinding = Keys.get(value.split("-")[0]);
                    Buttons buttonBinding = Buttons.get(value.split("-")[1]);
                    Commands.get(key).bind(buttonBinding, keyBinding);
                }
            }
        } catch (Exception e) {
            Logger.exception(e);
        }
    }

    public static void persistCommandsToConfig() {
        File input = Loader.get().data("input.cfg");
        List<String> lines = new ArrayList<String>();
        for (Command command : Commands.values()) {
            lines.add(command.name() + "," + command.key().name() + "-" + command.button().name());
        }
        try {
            FileUtils.writeLines(input, lines);
        } catch (IOException e) {
            Logger.exception(e, false);
        }
    }
}
