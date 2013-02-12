package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;
import org.apache.commons.io.FileUtils;
import sps.core.Loader;
import sps.core.Logger;

import java.util.ArrayList;
import java.util.List;

public class PlayerClassRegistry {

    private static PlayerClassRegistry __instance;

    public static PlayerClassRegistry get() {
        if (__instance == null) {
            __instance = new PlayerClassRegistry();
        }
        return __instance;
    }

    private PlayerClassRegistry() {
        try {
            for (String line : FileUtils.readLines(Loader.get().data("classes.csv"))) {
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
                    Stats stats = new Stats(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6), r.get(7), r.get(8), r.get(9), r.get(10), r.get(11));

                    pClass.Info = new PlayerClassInfo(skills, stats);
                }
            }
        } catch (Exception e) {
            Logger.exception("Error occurred while parsing " + Loader.get().data("classes.csv"), e);
        }
    }
}
