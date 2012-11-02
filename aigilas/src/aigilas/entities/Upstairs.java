package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.dungeons.Dungeon;
import aigilas.management.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Upstairs extends Entity {
    public Upstairs(Point2 location) {
        initialize(location, SpriteType.Upstairs, EntityType.Upstairs, DrawDepth.Stairs);
    }

    BaseCreature player;

    @Override
    public void update() {
        player = (BaseCreature) EntityManager.get().getTouchingCreature(this);
        if (player != null) {
            if (player.isInteracting()) {
                player.performInteraction();
                Dungeon.getPreviousFloor();
            }
        }
    }
}
