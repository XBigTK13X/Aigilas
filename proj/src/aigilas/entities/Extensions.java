package aigilas.entities;

import aigilas.creatures.BaseCreature;
import sps.bridge.EntityType;
import sps.entities.IEntity;

public class Extensions {
    public static BaseCreature isCreature(IEntity entity) {
        if (entity.getEntityType() == EntityType.ACTOR) {
            return (BaseCreature) entity;
        }
        return null;
    }
}
