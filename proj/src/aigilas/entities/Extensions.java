package aigilas.entities;

import aigilas.creatures.ICreature;
import spx.bridge.EntityType;
import spx.entities.IEntity;

public class Extensions {
    public static ICreature IsCreature(IEntity entity) {
        if (entity.GetEntityType() == EntityType.ACTOR) {
            return (ICreature) entity;
        }
        return null;
    }
}
