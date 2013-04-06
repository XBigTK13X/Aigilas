package aigilas.entities;

import aigilas.Aigilas;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.entities.Entity;

public class Wall extends Entity {
    public Wall(Point2 location) {
        initialize(location, SpriteTypes.get(Aigilas.Entities.Wall), EntityTypes.get(Aigilas.Entities.Wall), DrawDepths.get(Aigilas.Entities.Wall));
        _isBlocking = true;
        _graphic.setAnimationEnabled(false);
        _graphic.setDynamicEdges(true);
    }
}