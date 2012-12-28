package aigilas.entities;

import aigilas.Common;
import aigilas.creatures.BaseCreature;
import aigilas.dungeons.Dungeon;
import sps.bridge.ActorTypes;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Core;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;

public class Downstairs extends Entity {
    public Downstairs(Point2 location) {
        initialize(location, SpriteTypes.get(Common.Entities.Downstairs), EntityTypes.get(Common.Entities.Downstairs), DrawDepths.get(Common.DrawDepths.Stairs));
    }

    boolean disabled = false;
    private BaseCreature player;

    @Override
    public void update() {
        int nonPlayerCount = EntityManager.get().getActors(ActorTypes.get(Core.Non_Player)).size();
        int dummyCount = EntityManager.get().getActors(ActorTypes.get(Common.Actors.Dummy)).size();
        int minionCount = EntityManager.get().getActors(ActorTypes.get(Common.Actors.Minion)).size();

        if (nonPlayerCount - dummyCount - minionCount > 0) {
            disabled = true;
            _graphic.setAlpha(0);
        }
        else {
            _graphic.setAlpha(1);
            disabled = false;
        }

        if (!disabled) {
            player = (BaseCreature) EntityManager.get().getTouchingCreature(this);
            if (player != null) {
                if (player.isInteracting()) {
                    player.performInteraction();
                    Dungeon.getNextFloor();
                }
            }
        }
    }
}
