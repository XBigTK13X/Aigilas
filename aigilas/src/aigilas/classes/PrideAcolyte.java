package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class PrideAcolyte extends CreatureClass {
    public PrideAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        add(1, SkillId.STRENGTH_UP);
        add(2, SkillId.MANA_UP);
        add(3, SkillId.ELECTRIFY);
        add(4, SkillId.WALL_PUNCH);
        add(5, SkillId.MIMIC);
        add(6, SkillId.VALEDICTORIAN);
    }
}
