package aigilas.entities;

import aigilas.creatures.BaseCreature;
import sps.bridge.EntityType;
import sps.entities.Entity;

public class Extensions {
    public static BaseCreature isCreature(Entity entity) {
        if (entity.getEntityType() == EntityType.ACTOR) {
            return (BaseCreature) entity;
        }
        return null;
    }
}
