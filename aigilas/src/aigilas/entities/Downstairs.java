package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.dungeons.Dungeon;
import aigilas.energygement.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Downstairs extends Entity {
    public Downstairs(Point2 location) {
        initialize(location, SpriteType.Downstairs, EntityType.Downstairs, DrawDepth.Stairs);
    }

    private BaseCreature player;

    @Override
    public void update() {
        player = (BaseCreature) EntityManager.get().getTouchingCreature(this);
        if (player != null) {
            if (player.isInteracting()) {
                player.performInteraction();
                try {
                    Dungeon.getNextFloor();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
