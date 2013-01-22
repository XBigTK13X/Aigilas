package aigilas.entities;

import aigilas.Aigilas;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.entities.Entity;

public class EntityFactory {
    public static Entity create(EntityType type, Point2 location) {
        if (EntityTypes.get(Sps.Entities.Floor) == type) {
            return new Floor(location);
        }
        if (EntityTypes.get(Aigilas.Entities.Wall) == type) {
            return new Wall(location);
        }
        if (EntityTypes.get(Aigilas.Entities.Downstairs) == type) {
            return new Downstairs(location);
        }
        if (EntityTypes.get(Aigilas.Entities.Upstairs) == type) {
            return new Upstairs(location);
        }
        return null;
    }
}
