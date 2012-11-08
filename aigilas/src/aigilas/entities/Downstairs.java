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

public class Downstairs extends Entity {
    public Downstairs(Point2 location) {
        initialize(location, SpriteTypes.get(Common.Downstairs), EntityTypes.get(Common.Downstairs), DrawDepths.get(Common.Stairs));
    }

    private BaseCreature player;

    @Override
    public void update() {
        player = (BaseCreature) EntityManager.get().getTouchingCreature(this);
        if (player != null) {
            if (player.isInteracting()) {
                player.performInteraction();
                Dungeon.getNextFloor();
            }
        }
    }
}
