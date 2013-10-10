package aigilas.dungeons;

import aigilas.Aigilas;
import aigilas.entities.Darkness;
import aigilas.entities.Floor;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.core.SpsConfig;
import sps.entities.Entity;
import sps.entities.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityCache {
    private static EntityCache instance = new EntityCache();

    public static EntityCache get() {
        return instance;
    }

    private List<Entity> _cache = new ArrayList<Entity>();
    private HashMap<EntityType, List<Entity>> reusableTiles;

    private EntityCache() {
        reusableTiles = new HashMap<EntityType, List<Entity>>();
        reusableTiles.put(EntityTypes.get(Sps.Entities.Floor), new ArrayList<Entity>());
        reusableTiles.put(EntityTypes.get(Aigilas.Entities.Darkness), new ArrayList<Entity>());
        for (int ii = 1; ii < SpsConfig.get().tileMapWidth - 1; ii++) {
            for (int jj = 1; jj < SpsConfig.get().tileMapHeight - 1; jj++) {
                reusableTiles.get(EntityTypes.get(Sps.Entities.Floor)).add(new Floor(new Point2(ii, jj)));
                reusableTiles.get(EntityTypes.get(Aigilas.Entities.Darkness)).add(new Darkness(new Point2(ii, jj)));
            }
        }
    }

    public List<Entity> getReusable(EntityType entityType) {
        return reusableTiles.get(entityType);
    }

    public boolean isReusable(EntityType entityType) {
        return reusableTiles.containsKey(entityType);
    }

    public void addToCache(Entity content) {
        _cache.add(content);
    }

    public List<Entity> flushCache() {
        ArrayList<Entity> result = new ArrayList<Entity>(_cache);
        _cache.clear();
        return result;
    }

    public List<Entity> getEntitiesToCache() {
        List<Entity> results = new ArrayList<Entity>();
        for (Entity _content : EntityManager.get().getAllEntities()) {
            if (!reusableTiles.keySet().contains(_content.getEntityType())) {
                results.add(_content);
            }
        }
        return results;
    }
}
