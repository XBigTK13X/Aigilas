package aigilas.entities;

import aigilas.creatures.ICreature;
import spx.bridge.EntityType;
import spx.entities.IEntity;

public class Extensions {
    public static ICreature isCreature(IEntity entity) {
        if (entity.getEntityType() == EntityType.ACTOR) {
            return (ICreature) entity;
        }
        return null;
    }
}
