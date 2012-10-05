package aigilas.classes;

import aigilas.skills.SkillId;

public class SkillLevel {

    public final int Level;
    public final SkillId Skill;

    public SkillLevel(int level,SkillId skill){
        Skill = skill;
        Level = level;
    }
}
