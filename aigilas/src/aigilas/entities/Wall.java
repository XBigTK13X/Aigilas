package aigilas.entities;

import aigilas.management.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;

public class Wall extends Entity {
    public Wall(Point2 location) {
        initialize(location, SpriteType.Wall, EntityType.Wall, DrawDepth.Wall);
        _isBlocking = true;
    }
}
