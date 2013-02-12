package aigilas.statuses;

import org.apache.commons.io.FileUtils;
import sps.core.Loader;
import sps.core.Logger;

public class StatusRegistry {
    private static StatusRegistry __instance;

    public static StatusRegistry get() {
        if (__instance == null) {
            __instance = new StatusRegistry();
        }
        return __instance;
    }

    private StatusRegistry() {
        try {
            for (String line : FileUtils.readLines(Loader.get().data("statuses.csv"))) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    String name = values[0];
                    int magnitude = Integer.parseInt(values[1]);
                    Status.get(name).Info = new StatusInfo(name, magnitude);
                }
            }
        } catch (Exception e) {
            Logger.exception("Error occurred while parsing statuses.csv.", e);
        }
    }
}
