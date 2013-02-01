package aigilas.dungeons;

import aigilas.Aigilas;
import aigilas.entities.Darkness;
import aigilas.entities.Floor;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.bridge.Sps;
import sps.core.Logger;
import sps.core.Point2;
import sps.core.Settings;
import sps.entities.Entity;
import sps.entities.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityCache {
    private static EntityCache instance = new EntityCache();

    public static EntityCache get(){
        return instance;
    }

    private List<Entity> _cache = new ArrayList<Entity>();
    private HashMap<EntityType, List<Entity>> staticTileCache;
    private List<EntityType> cacheIgnore = new ArrayList<EntityType>();

    private EntityCache(){
        staticTileCache = new HashMap<EntityType, List<Entity>>();
        staticTileCache.put(EntityTypes.get(Sps.Entities.Floor), new ArrayList<Entity>());
        staticTileCache.put(EntityTypes.get(Aigilas.Entities.Darkness), new ArrayList<Entity>());
        for (int ii = 1; ii < Settings.get().tileMapWidth - 1; ii++) {
            for (int jj = 1; jj < Settings.get().tileMapHeight - 1; jj++) {
                staticTileCache.get(EntityTypes.get(Sps.Entities.Floor)).add(new Floor(new Point2(ii, jj)));
                staticTileCache.get(EntityTypes.get(Aigilas.Entities.Darkness)).add(new Darkness(new Point2(ii, jj)));
            }
        }
    }

    public List<Entity> retrieve(EntityType entityType){
        return staticTileCache.get(entityType);
    }

    public List<Entity> retrieveCache(){
        Logger.info("Reading: " +count);
        return _cache;
    }

    private int count = 0;
    public void addToCache(Entity content) {
        Logger.info("Caching: " +count++);
        _cache.add(content);
    }

    public List<Entity> flushCache() {
        ArrayList<Entity> result = new ArrayList<Entity>(_cache);
        _cache.clear();
        return result;
    }

    public boolean staticallyCaches(EntityType entityType){
        return staticTileCache.containsKey(entityType);
    }

    public void addCacheType(EntityType entityType) {
        cacheIgnore.add(entityType);
    }

    public List<Entity> getEntitiesToCache() {
        List<Entity> results = new ArrayList<Entity>();
        for (Entity _content : EntityManager.get().getAllEntities()) {
            if (!cacheIgnore.contains(_content.getEntityType())) {
                results.add(_content);
            }
        }
        return results;
    }
}
