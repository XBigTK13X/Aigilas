package aigilas.creatures;

import com.badlogic.gdx.Gdx;
import sps.bridge.ActorType;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class StatsRegistry {
    private static StatsRegistry __instance;
    private static String __skillsDataPath = "assets/data/base_stats.csv";

    public static StatsRegistry get() {
        if (__instance == null) {
            __instance = new StatsRegistry();
        }
        return __instance;
    }

    private HashMap<ActorType, Stats> baseStats = new HashMap<ActorType, Stats>();

    private StatsRegistry() {
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(__skillsDataPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    String convert = line.replace(",,", ",-,");

                    String[] values = convert.split(",");
                    ActorType actorType = ActorType.get(values[0]);
                    String[] raw = values[1].split("-");
                    int[] r = new int[11];
                    for (int ii = 0; ii < raw.length; ii++) {
                        r[ii] = Integer.parseInt(raw[ii]);
                    }

                    Stats stats = new Stats(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8], r[9], r[10]);

                    baseStats.put(actorType, stats);
                }
            }
            in.close();
        }
        catch (Exception e) {
            Logger.error("Error occurred while parsing base_stats.csv.");
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public Stats baseStats(ActorType actorType) {
        if (baseStats.containsKey(actorType)) {
            return new Stats(baseStats.get(actorType));
        }
        return new Stats(baseStats.get(ActorType.Non_Player));
    }
}
