package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

import java.util.List;

public class PlayerClassInfo {
    public List<SkillLevel> Skills;
    public Stats Stats;

    public PlayerClassInfo(List<SkillLevel> skills, Stats stats){
        Stats = stats;
        Skills = skills;
    }
}
