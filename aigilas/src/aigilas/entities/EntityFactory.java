package aigilas.entities;

import aigilas.Common;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.core.Core;
import sps.core.Point2;
import sps.entities.Entity;

public class EntityFactory {
    public static Entity create(EntityType type, Point2 location) {
        if (EntityTypes.get(Core.Floor) == type) {
            return new Floor(location);
        }
        if (EntityTypes.get(Common.Entities.Wall) == type) {
            return new Wall(location);
        }
        if (EntityTypes.get(Common.Entities.Downstairs) == type) {
            return new Downstairs(location);
        }
        if (EntityTypes.get(Common.Entities.Upstairs) == type) {
            return new Upstairs(location);
        }
        return null;
    }
}
