package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class GreedAcolyte extends CreatureClass {
	public GreedAcolyte() {
		super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
		Add(1, SkillId.SOUL_REINFORCEMENT);
		Add(2, SkillId.HORDER);
		Add(3, SkillId.SPAWN_ITEM);
		Add(4, SkillId.THROW_ITEM);
		Add(5, SkillId.STEAL_ITEM);
	}
}
