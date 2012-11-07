package aigilas.entities;

import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteType;
import sps.core.Point2;
import sps.entities.Entity;

public class Wall extends Entity {
    public Wall(Point2 location) {
        initialize(location, SpriteType.Wall, EntityTypes.get("Wall"), DrawDepths.get("Wall"));
        _isBlocking = true;
    }
}
