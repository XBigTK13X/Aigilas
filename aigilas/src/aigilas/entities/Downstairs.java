package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.dungeons.DungeonFactory;
import aigilas.management.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Downstairs extends Entity {
    public Downstairs(Point2 location) {
        initialize(location, SpriteType.DOWNSTAIRS, EntityType.DOWNSTAIRS, DrawDepth.Stairs);
    }

    private BaseCreature player;

    @Override
    public void update() {
        player = (BaseCreature) EntityManager.get().getTouchingCreature(this);
        if (player != null) {
            if (player.isInteracting()) {
                player.performInteraction();
                try {
                    DungeonFactory.getNextFloor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
