package aigilas.entities;

import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;

public class EntityFactory {
    public static Entity create(EntityType type, Point2 location) {
        switch (type) {
            case FLOOR:
                return new Floor(location);
            case WALL:
                return new Wall(location);
            case DOWNSTAIRS:
                return new Downstairs(location);
            case UPSTAIRS:
                return new Upstairs(location);
            default:
                try {
                    throw new Exception("An undefined int case was passed into the EntityFactory.");
                } catch (Exception e) {

                    e.printStackTrace();
                }

                return null;
        }
    }
}
