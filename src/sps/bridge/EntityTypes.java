package sps.bridge;

import java.util.HashMap;
import java.util.Map;

public class EntityTypes {

    private static EntityTypes instance;

    public static EntityType get(String name) {
        return instance.resolve(name);
    }

    public static void add(EntityType EntityType) {
        if (instance == null) {
            instance = new EntityTypes();
        }
        instance.put(EntityType);
    }

    private Map<String, EntityType> EntityTypes = new HashMap<String, EntityType>();

    private EntityTypes() {
    }

    public EntityType resolve(String name) {
        return EntityTypes.get(name);
    }

    public void put(EntityType EntityType) {
        EntityTypes.put(EntityType.Name, EntityType);
    }
}
