package aigilas.entities;

import aigilas.creatures.ICreature;
import aigilas.dungeons.DungeonFactory;
import aigilas.management.SpriteType;
import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;
import spx.entities.EntityManager;

public class Upstairs extends Entity {
    public Upstairs(Point2 location) {
        Initialize(location, SpriteType.UPSTAIRS, EntityType.UPSTAIRS, DrawDepth.Stairs);
    }

    ICreature player;

    @Override
    public void Update() {
        player = (ICreature) EntityManager.GetTouchingCreature(this);
        if (player != null) {
            if (player.IsInteracting()) {
                player.PerformInteraction();
                try {
                    DungeonFactory.GetPreviousFloor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
