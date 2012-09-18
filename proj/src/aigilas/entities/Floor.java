package aigilas.entities;

import aigilas.management.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;

public class Floor extends Entity {
    public Floor(Point2 location) {
        initialize(location, SpriteType.FLOOR, EntityType.FLOOR, DrawDepth.Floor);
    }
}
