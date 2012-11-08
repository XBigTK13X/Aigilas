package aigilas.entities;

import aigilas.Common;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.entities.Entity;

public class Wall extends Entity {
    public Wall(Point2 location) {
        initialize(location, SpriteTypes.get(Common.Wall), EntityTypes.get(Common.Wall), DrawDepths.get(Common.Wall));
        _isBlocking = true;
    }
}
