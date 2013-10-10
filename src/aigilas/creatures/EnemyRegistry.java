package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import org.apache.commons.io.FileUtils;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.core.Loader;
import sps.core.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnemyRegistry {
    private static EnemyRegistry __instance;

    public static EnemyRegistry get() {
        if (__instance == null) {
            __instance = new EnemyRegistry();
        }
        return __instance;
    }

    private HashMap<ActorType, EnemyInfo> info = new HashMap<ActorType, EnemyInfo>();

    private EnemyRegistry() {
        try {
            for (String line : FileUtils.readLines(Loader.get().data("enemies.csv"))) {
                if (!line.contains("#")) {
                    String convert = line.replace(",,", ",-,");

                    String[] values = convert.split(",");
                    ActorType actorType = ActorTypes.get(values[0]);

                    String rawStrengths = values[1];
                    List<StatType> strengths = new ArrayList<StatType>();
                    for (String stat : rawStrengths.split("-")) {
                        strengths.add(StatType.getShort(stat));
                    }

                    String rawWeaknesses = values[2];
                    List<StatType> weaknesses = new ArrayList<StatType>();
                    for (String stat : rawWeaknesses.split("-")) {
                        weaknesses.add(StatType.getShort(stat));
                    }

                    String rawElements = values[3];
                    List<Elements> elements = new ArrayList<Elements>();
                    for (String element : rawElements.split("-")) {
                        elements.add(Elements.get(element));
                    }

                    List<SkillId> skills = new ArrayList<SkillId>();
                    if (values.length == 5) {
                        String rawSkills = values[4];
                        for (String skill : rawSkills.split("-")) {
                            skills.add(SkillId.get(skill));
                        }
                    } else {
                        skills.add(SkillId.No_Skill);
                    }

                    info.put(actorType, new EnemyInfo(strengths, weaknesses, elements, skills));
                }
            }
        } catch (Exception e) {
            Logger.exception("Error occurred while parsing enemies.csv.", e);
        }
    }

    public boolean contains(ActorType actorType) {
        return info.containsKey(actorType);
    }

    public EnemyInfo getInfo(ActorType actorType) {
        return info.get(actorType);
    }
}
