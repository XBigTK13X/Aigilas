package aigilas.entities;

import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteType;
import sps.core.Point2;
import sps.entities.Entity;

public class Floor extends Entity {
    public Floor(Point2 location) {
        initialize(location, SpriteType.Floor, EntityTypes.get("Floor"), DrawDepths.get("Floor"));
    }
}
