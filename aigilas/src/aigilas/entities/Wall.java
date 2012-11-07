package aigilas.entities;

import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.entities.Entity;

public class Wall extends Entity {
    public Wall(Point2 location) {
        initialize(location, SpriteTypes.get("Wall"), EntityTypes.get("Wall"), DrawDepths.get("Wall"));
        _isBlocking = true;
    }
}
