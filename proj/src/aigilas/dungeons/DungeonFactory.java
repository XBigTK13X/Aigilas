package aigilas.dungeons;

import aigilas.creatures.CreatureFactory;
import spx.entities.Entity;
import spx.entities.IEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonFactory {

    private static int __floorCount = 0;

    private static HashMap<Location, DungeonSet> _world = new HashMap<>();
    private static List<Entity> _cache = new ArrayList<>();

    public static void GetNextFloor() {
        _world.get(Location.Depths).GotoNext();
    }

    public static boolean GetPreviousFloor() {
        return _world.get(Location.Depths).GotoPrevious();
    }

    public static void AddToCache(Entity content) {
        _cache.add(content);
    }

    public static List<IEntity> FlushCache() {
        ArrayList<IEntity> result = new ArrayList<IEntity>(_cache);
        _cache.clear();
        return result;
    }

    public static void Start() {
        _world = new HashMap<>();
        _cache = new ArrayList<>();
        _world.put(Location.Depths, new DungeonSet());
        while (CreatureFactory.BossesRemaining() > 0) {
            GetNextFloor();
        }
        while (GetPreviousFloor()) {
        }
    }

    public static int GetFloorCount() {
        return __floorCount;
    }

    public static void IncreaseFloorCount() {
        __floorCount++;
    }
}
