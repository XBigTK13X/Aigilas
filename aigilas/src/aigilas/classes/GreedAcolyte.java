package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class GreedAcolyte extends CreatureClass {
    public GreedAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        add(1, SkillId.SOUL_REINFORCEMENT);
        add(2, SkillId.HORDER);
        add(3, SkillId.SPAWN_ITEM);
        add(4, SkillId.THROW_ITEM);
        add(5, SkillId.STEAL_ITEM);
    }
}
