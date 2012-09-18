package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.dungeons.DungeonFactory;
import aigilas.management.SpriteType;
import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;
import spx.entities.EntityManager;

public class Downstairs extends Entity {
    public Downstairs(Point2 location) {
        initialize(location, SpriteType.DOWNSTAIRS, EntityType.DOWNSTAIRS, DrawDepth.Stairs);
    }

    private BaseCreature player;

    @Override
    public void update() {
        player = (BaseCreature) EntityManager.getTouchingCreature(this);
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
