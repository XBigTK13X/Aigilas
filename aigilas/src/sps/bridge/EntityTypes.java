package sps.bridge;

import sps.core.Logger;

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
        if (!EntityTypes.containsKey(name.toLowerCase())) {
            Logger.exception("The entityType " + name + " is not defined.", new Exception("Add it to bridge.cfg"));
        }
        return EntityTypes.get(name.toLowerCase());
    }

    public void put(EntityType EntityType) {
        EntityTypes.put(EntityType.Name, EntityType);
    }
}