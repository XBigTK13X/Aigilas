package aigilas.entities;

import aigilas.management.SpriteType;
import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;

public class Floor extends Entity {
    public Floor(Point2 location) {
        Initialize(location, SpriteType.FLOOR, EntityType.FLOOR, DrawDepth.Floor);
    }
}
