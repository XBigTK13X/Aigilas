package aigilas.skills;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import org.apache.commons.io.FileUtils;
import sps.core.Loader;
import sps.core.Logger;

import java.util.ArrayList;
import java.util.List;

public class SkillRegistry {
    private static SkillRegistry __instance;

    public static SkillRegistry get() {
        if (__instance == null) {
            __instance = new SkillRegistry();
        }
        return __instance;
    }

    private SkillRegistry() {
        try {
            for (String line : FileUtils.readLines(Loader.get().data("skills.csv"))) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    String name = values[0];
                    StatType stat = StatType.get(values[2]);
                    int cost = Integer.parseInt(values[1]);
                    String rawElements = values[3];
                    List<Elements> elements = new ArrayList<Elements>();
                    for (String element : rawElements.split("-")) {
                        elements.add(Elements.get(element));
                    }
                    int magnitude = Integer.parseInt(values[4]);
                    boolean restrict = false;
                    boolean offCenter = false;
                    if (values.length > 5) {
                        String rawComments = values[5];
                        if (rawComments.contains("Boss") || rawComments.contains("Restrict")) {
                            restrict = true;
                        }
                        if (rawComments.contains("OffCenter")) {
                            offCenter = true;
                        }
                    }
                    SkillId.get(name).Info = new SkillInfo(name.replace("_", " "), stat, cost, magnitude, elements, restrict, offCenter);
                }
            }
        } catch (Exception e) {
            Logger.exception("Error occurred while parsing skills.csv.", e);
        }
    }
}
