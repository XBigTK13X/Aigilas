package aigilas.entities;

import aigilas.Aigilas;
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
        initialize(location, SpriteTypes.get(Aigilas.Entities.Upstairs), EntityTypes.get(Aigilas.Entities.Upstairs), DrawDepths.get(Aigilas.DrawDepths.Stairs));
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
