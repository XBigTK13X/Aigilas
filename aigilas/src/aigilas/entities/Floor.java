package aigilas.entities;

import aigilas.Common;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Core;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Floor extends Entity {
    public Floor(Point2 location) {
        initialize(location, SpriteTypes.get(Core.Entities.Floor), EntityTypes.get(Core.Entities.Floor), DrawDepths.get(Core.Entities.Floor));
    }
}
