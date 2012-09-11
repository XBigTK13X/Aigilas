package aigilas.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aigilas.creatures.StatType;
import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public abstract class CreatureClass {
	private Stats _stats;
	protected HashMap<Integer, SkillId> _skillUnlocks = new HashMap<Integer, SkillId>();
	public static CreatureClass NULL = new NoClass();

	protected CreatureClass() {
	}

	protected CreatureClass(Stats stats) {
		_stats = new Stats(stats);
	}

	public float GetBonus(int level, StatType stat) {
		return _stats.GetBonus(level, stat);
	}

	public List<SkillId> GetLevelSkills(int level) {
		List<SkillId> results = new ArrayList<SkillId>();
		for (Integer key : _skillUnlocks.keySet()) {
			if (key <= level) {
				results.add(_skillUnlocks.get(key));
			}
		}
		return results;
	}

	protected void Add(int level, SkillId skillId) {
		_skillUnlocks.put(level, skillId);
	}
}
