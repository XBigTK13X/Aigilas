package aigilas.creatures;

import org.apache.commons.io.FileUtils;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.bridge.Sps;
import sps.core.Loader;
import sps.core.Logger;

import java.util.HashMap;

public class StatsRegistry {
    private static StatsRegistry __instance;

    public static StatsRegistry get() {
        if (__instance == null) {
            __instance = new StatsRegistry();
        }
        return __instance;
    }

    private HashMap<ActorType, Stats> baseStats = new HashMap<ActorType, Stats>();

    private StatsRegistry() {
        try {
            for (String line : FileUtils.readLines(Loader.get().data("base_stats.csv"))) {
                if (!line.contains("#")) {
                    String convert = line.replace(",,", ",-,");

                    String[] values = convert.split(",");
                    ActorType actorType = ActorTypes.get(values[0]);
                    String[] raw = values[1].split("-");
                    int[] r = new int[12];
                    for (int ii = 0; ii < raw.length; ii++) {
                        r[ii] = Integer.parseInt(raw[ii]);
                    }

                    Stats stats = new Stats(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8], r[9], r[10], r[11]);

                    baseStats.put(actorType, stats);
                }
            }
        } catch (Exception e) {
            Logger.exception("Error occurred while parsing base_stats.csv.", e);
        }
    }

    public Stats baseStats(ActorType actorType) {
        if (baseStats.containsKey(actorType)) {
            return new Stats(baseStats.get(actorType));
        }
        return new Stats(baseStats.get(ActorTypes.get(Sps.ActorGroups.Non_Player)));
    }
}
