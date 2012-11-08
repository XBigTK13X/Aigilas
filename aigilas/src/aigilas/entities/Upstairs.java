package aigilas.entities;

import aigilas.Common;
import aigilas.creatures.BaseCreature;
import aigilas.dungeons.Dungeon;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Upstairs extends Entity {
    public Upstairs(Point2 location) {
        initialize(location, SpriteTypes.get(Common.Upstairs), EntityTypes.get(Common.Upstairs), DrawDepths.get(Common.Stairs));
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
