package aigilas.entities;

import aigilas.Common;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.Settings;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.HitTest;

public class Darkness extends Entity {
    private float transparency;

    public Darkness(Point2 location) {
        initialize(location, SpriteTypes.get(Common.Entities.Darkness), EntityTypes.get(Common.Entities.Darkness), DrawDepths.get(Common.Entities.Darkness));
        changeOpacity();
    }

    boolean playerNear;

    @Override
    public void update() {
        Entity player = (Entity) EntityManager.get().getNearestPlayer(this);
        if (player.isActive()) {
            float distance = HitTest.getDistanceSquare(player, this);
            if (distance < Settings.get().spriteWidth * Settings.get().spriteWidth * 7) {
                _graphic.setAlpha(0);
                playerNear = true;
            }
            else {
                _graphic.setAlpha(transparency);
                playerNear = false;
            }
        }
        else {
            _graphic.setAlpha(transparency);
            playerNear = false;
        }
    }

    public boolean beingLit() {
        return playerNear;
    }

    public void changeOpacity() {
        transparency = RNG.next(30, 70, false) / 100f;
    }

    @Override
    public void draw() {
        if (!playerNear) {
            super.draw();
        }
    }
}
