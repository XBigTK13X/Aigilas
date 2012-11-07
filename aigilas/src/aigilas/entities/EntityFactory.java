package aigilas.entities;

import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.core.Point2;
import sps.entities.Entity;

public class EntityFactory {
    public static Entity create(EntityType type, Point2 location) {
        if (EntityTypes.get("Floor") == type) {
            return new Floor(location);
        }
        if (EntityTypes.get("Wall") == type) {
            return new Wall(location);
        }
        if (EntityTypes.get("Downstairs") == type) {
            return new Downstairs(location);
        }
        if (EntityTypes.get("Upstairs") == type) {
            return new Upstairs(location);
        }
        return null;
    }
}
