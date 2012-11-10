package aigilas.entities;

import aigilas.creatures.BaseCreature;
import sps.bridge.EntityTypes;
import sps.core.Core;
import sps.entities.Entity;

public class Extensions {
    public static BaseCreature isCreature(Entity entity) {
        if (entity.getEntityType() == EntityTypes.get(Core.Actor)) {
            return (BaseCreature) entity;
        }
        return null;
    }
}
