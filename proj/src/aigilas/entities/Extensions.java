package aigilas.entities;

import aigilas.creatures.BaseCreature;
import spx.bridge.EntityType;
import spx.entities.IEntity;

public class Extensions {
    public static BaseCreature isCreature(IEntity entity) {
        if (entity.getEntityType() == EntityType.ACTOR) {
            return (BaseCreature) entity;
        }
        return null;
    }
}
