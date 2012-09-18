package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class EnvyAcolyte extends CreatureClass {
    public EnvyAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        add(1, SkillId.CONFUSION);
        add(2, SkillId.WEAK_KNEEES);
        add(3, SkillId.VENOM_FIST);
        add(4, SkillId.ABSORB);
        add(5, SkillId.MUTINY);
    }
}
