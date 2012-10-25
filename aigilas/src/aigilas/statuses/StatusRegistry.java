package aigilas.statuses;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.skills.SkillInfo;
import com.badlogic.gdx.Gdx;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
            Logger.error("Error occurred while parsing statuses.csv.");
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
}
