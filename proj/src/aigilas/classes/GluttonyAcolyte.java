package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class GluttonyAcolyte extends CreatureClass {
    public GluttonyAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        Add(1, SkillId.FORGET_SKILL);
    }
}
