package aigilas.classes; import com.badlogic.gdx.graphics.Color;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import aigilas.creatures.Stats;public abstract class CreatureClass {	private Stats _stats;	protected HashMap<Integer, String> _skillUnlocks = new HashMap<Integer, String>();	public static CreatureClass NULL = new NoClass();	protected CreatureClass() {	}	protected CreatureClass(Stats stats) {		_stats = new Stats(stats);	}	public float GetBonus(int level, String stat) {		return _stats.GetBonus(level, stat);	}	public List<String> GetLevelSkills(int level) {		List<String> results = new ArrayList<String>();		for (Integer key : _skillUnlocks.keySet()) {			if (key <= level) {				results.add(_skillUnlocks.get(key));			}		}		return results;	}	protected void Add(int level, String skillId) {		_skillUnlocks.put(level, skillId);	}}