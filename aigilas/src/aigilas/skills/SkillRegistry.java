package aigilas.skills;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.core.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SkillRegistry {
    private static SkillRegistry __instance;
    private static String __skillsDataPath = "assets/data/skills.csv";

    public static SkillRegistry get() {
        if (__instance == null) {
            __instance = new SkillRegistry();
        }
        return __instance;
    }

    private SkillRegistry() {
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
            in.close();
        }
        catch (Exception e) {
            Logger.exception("Error occurred while parsing skills.csv.", e);
        }
    }
}
