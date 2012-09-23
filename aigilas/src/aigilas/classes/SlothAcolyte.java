package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class SlothAcolyte extends CreatureClass {
    public SlothAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        add(1, SkillId.ACID_NOZZLE);
        add(2, SkillId.FLOOR_SPIKES);
        add(3, SkillId.REMOTE_MINE);
        add(4, SkillId.VAPOR_IMPLANT);
        add(1, SkillId.DART_TRAP);
    }
}
