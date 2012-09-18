package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class WrathAcolyte extends CreatureClass {
    public WrathAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        add(1, SkillId.STRENGTH_UP);
        add(1, SkillId.MANA_UP);
        add(1, SkillId.FLAME_HAMMER);
        add(2, SkillId.GUSH);
        add(3, SkillId.SOUL_CRUSH);
        add(4, SkillId.COMBUST);
        add(5, SkillId.HORRIFY);
    }
}
