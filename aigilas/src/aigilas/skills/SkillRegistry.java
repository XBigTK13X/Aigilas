package aigilas.skills;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    private HashMap<SkillId, SkillInfo> skills;

    private SkillRegistry() {
        skills = new HashMap<SkillId, SkillInfo>();
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(__skillsDataPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    String[] values = line.split(",");
                    System.out.println("Initing " + line);
                    String name = values[0];
                    StatType stat = StatType.get(values[2]);
                    float cost = Float.parseFloat(values[1]);
                    String rawElements = values[3];
                    List<Elements> elements = new ArrayList<Elements>();
                    for (String element : rawElements.split("-")) {
                        elements.add(Elements.get(element));
                    }
                    float magnitude = Float.parseFloat(values[4]);
                    boolean bossOnly = false;
                    boolean disabled = false;
                    if (values.length == 6) {
                        String rawComments = values[5];
                        if (rawComments.contains("Boss")) {
                            bossOnly = true;
                        }
                    }
                    SkillInfo info = null;
                    if (!disabled) {
                        info = new SkillInfo(name, stat, cost, magnitude, elements, bossOnly);
                    }
                    if (info != null) {
                        skills.put(SkillId.get(name), info);
                        SkillId.get(name).Info = skills.get(SkillId.get(name));
                    }
                }
            }
            in.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred while parsing skills.csv.");
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
}
