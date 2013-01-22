package aigilas.entities;

import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.entities.Entity;

public class Floor extends Entity {
    public Floor(Point2 location) {
        initialize(location, SpriteTypes.get(Sps.Entities.Floor), EntityTypes.get(Sps.Entities.Floor), DrawDepths.get(Sps.Entities.Floor));
    }
}
