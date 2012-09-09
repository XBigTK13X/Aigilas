package aigilas.dungeons; import java.util.ArrayList;import java.util.HashMap;import java.util.List;import spx.entities.Entity;import spx.entities.IEntity;import aigilas.creatures.CreatureFactory;public class DungeonFactory {	public static int BlocksHigh = 20;	public static int BlocksWide = 30;	private static int __floorCount = 0;	private static HashMap<Integer, DungeonSet> _world = new HashMap<Integer, DungeonSet>();	private static List<Entity> _cache = new ArrayList<Entity>();	public static void GetNextFloor() {		_world.get(Location.DrawDepths).GotoNext();	}	public static boolean GetPreviousFloor() {		return _world.get(Location.DrawDepths).GotoPrevious();	}	public static void AddToCache(Entity content) {		_cache.add(content);	}	public static List<IEntity> FlushCache() {		ArrayList<IEntity> result = new ArrayList<IEntity>(_cache);		_cache.clear();		return result;	}	public static void Start() {		_world = new HashMap<Integer, DungeonSet>();		_cache = new ArrayList<Entity>();		_world.put(Location.DrawDepths, new DungeonSet());		while (CreatureFactory.BossesRemaining() > 0) {			GetNextFloor();		}		while (GetPreviousFloor()) {		}	}	public static int GetFloorCount() {		return __floorCount;	}	public static void IncreaseFloorCount() {		__floorCount++;	}}