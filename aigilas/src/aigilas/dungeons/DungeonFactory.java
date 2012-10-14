package aigilas.dungeons;

import aigilas.creatures.impl.CreatureFactory;
import sps.audio.MusicPlayer;
import sps.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonFactory {

    private static int __floorCount = 0;

    private static HashMap<Location, DungeonSet> _world = new HashMap<Location, DungeonSet>();
    private static List<Entity> _cache = new ArrayList<Entity>();

    public static void getNextFloor() {
        _world.get(Location.Depths).gotoNext();
    }

    public static boolean getPreviousFloor() {
        return _world.get(Location.Depths).gotoPrevious();
    }

    public static void addToCache(Entity content) {
        _cache.add(content);
    }

    public static List<Entity> flushCache() {
        ArrayList<Entity> result = new ArrayList<Entity>(_cache);
        _cache.clear();
        return result;
    }

    public static void start() {
        _world = new HashMap<Location, DungeonSet>();
        _cache = new ArrayList<Entity>();
        _world.put(Location.Depths, new DungeonSet());
        while (CreatureFactory.bossesRemaining() > 0) {
            getNextFloor();
        }
        while (getPreviousFloor()) {
        }
        MusicPlayer.get().start();
    }

    public static int getFloorCount() {
        return __floorCount;
    }

    public static void increaseFloorCount() {
        __floorCount++;
    }
}
