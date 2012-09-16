package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class LustAcolyte extends CreatureClass {
    public LustAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        add(1, SkillId.REGEN_ALL);
        add(2, SkillId.SPEED_UP);
        add(3, SkillId.ENVENOM);
        add(4, SkillId.COLD_SHOULDER);
        add(5, SkillId.CAVALRY);
    }
}
