package aigilas.entities;

import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;
import spx.entities.EntityManager;
import aigilas.creatures.ICreature;
import aigilas.dungeons.DungeonFactory;
import aigilas.management.SpriteType;

public class Downstairs extends Entity {
	public Downstairs(Point2 location) {
		Initialize(location, SpriteType.DOWNSTAIRS, EntityType.DOWNSTAIRS, DrawDepth.Stairs);
	}

	private ICreature player;

	@Override
	public void Update() {
		player = (ICreature) EntityManager.GetTouchingCreature(this);
		if (player != null) {
			if (player.IsInteracting()) {
				player.PerformInteraction();
				try {
					DungeonFactory.GetNextFloor();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
