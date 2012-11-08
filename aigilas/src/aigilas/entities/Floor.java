package aigilas.entities;

import aigilas.Common;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.entities.Entity;

public class Floor extends Entity {
    public Floor(Point2 location) {
        initialize(location, SpriteTypes.get(Common.Floor), EntityTypes.get(Common.Floor), DrawDepths.get(Common.Floor));
    }
}
