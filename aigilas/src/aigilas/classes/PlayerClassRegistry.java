package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;
import com.badlogic.gdx.Gdx;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PlayerClassRegistry {

    private static PlayerClassRegistry __instance;
    private static String __dataPath = "assets/data/classes.csv";

    public static PlayerClassRegistry get() {
        if (__instance == null) {
            __instance = new PlayerClassRegistry();
        }
        return __instance;
    }

    private PlayerClassRegistry() {
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(__dataPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    PlayerClass pClass = PlayerClass.get(values[0]);
                    List<Integer> r = new ArrayList<Integer>();
                    for (String stat : values[2].split("-")) {
                        r.add(Integer.parseInt(stat));
                    }

                    List<SkillLevel> skills = new ArrayList<SkillLevel>();
                    for (String skill : values[1].split("-")) {
                        int level = Integer.parseInt(skill.split(":")[0]);
                        SkillId sk = SkillId.get(skill.split(":")[1]);
                        skills.add(new SkillLevel(level, sk));
                    }
                    Stats stats = new Stats(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6), r.get(7), r.get(8), r.get(9));

                    pClass.Info = new PlayerClassInfo(skills, stats);
                }
            }
            in.close();
        }
        catch (Exception e) {
            Logger.error("Error occurred while parsing " + __dataPath);
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
}
