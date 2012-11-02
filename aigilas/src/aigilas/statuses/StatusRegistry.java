package aigilas.statuses;

import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class StatusRegistry {
    private static StatusRegistry __instance;
    private static String __skillsDataPath = "assets/data/statuses.csv";

    public static StatusRegistry get() {
        if (__instance == null) {
            __instance = new StatusRegistry();
        }
        return __instance;
    }

    private StatusRegistry() {
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(__skillsDataPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    String name = values[0];
                    int magnitude = Integer.parseInt(values[1]);
                    Status.get(name).Info = new StatusInfo(name, magnitude);
                }
            }
            in.close();
        }
        catch (Exception e) {
            Logger.exception("Error occurred while parsing statuses.csv.", e);
        }
    }
}
