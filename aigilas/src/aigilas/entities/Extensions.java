package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.management.Common;
import sps.bridge.EntityTypes;
import sps.entities.Entity;

public class Extensions {
    public static BaseCreature isCreature(Entity entity) {
        if (entity.getEntityType() == EntityTypes.get(Common.Actor)) {
            return (BaseCreature) entity;
        }
        return null;
    }
}
