package aigilas.entities;

import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;
import aigilas.management.SpriteType;

public class Wall extends Entity {
	public Wall(Point2 location) {
		Initialize(location, SpriteType.WALL, EntityType.WALL, DrawDepth.Wall);
		_isBlocking = true;
	}
}
