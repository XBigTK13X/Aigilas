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

    public static void getNextFloor() {
        _world.get(Location.Depths).gotoNext();
    }

    public static boolean getPreviousFloor() {
        return _world.get(Location.Depths).gotoPrevious();
    }

    public static void addToCache(Entity content) {
        _cache.add(content);
    }

    public static List<IEntity> flushCache() {
        ArrayList<IEntity> result = new ArrayList<IEntity>(_cache);
        _cache.clear();
        return result;
    }

    public static void start() {
        _world = new HashMap<>();
        _cache = new ArrayList<>();
        _world.put(Location.Depths, new DungeonSet());
        while (CreatureFactory.bossesRemaining() > 0) {
            getNextFloor();
        }
        while (getPreviousFloor()) {
        }
    }

    public static int getFloorCount() {
        return __floorCount;
    }

    public static void increaseFloorCount() {
        __floorCount++;
    }
}
