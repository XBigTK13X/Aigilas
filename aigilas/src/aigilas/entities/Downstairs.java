package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.dungeons.Dungeon;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteType;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Downstairs extends Entity {
    public Downstairs(Point2 location) {
        initialize(location, SpriteType.Downstairs, EntityTypes.get("Downstairs"), DrawDepths.get("Stairs"));
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
