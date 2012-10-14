package aigilas.skills.impl;

import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.EntityType;
import sps.core.Settings;
import sps.entities.Entity;

public class WallPunchSkill extends BaseSkill {
    public WallPunchSkill()

    {
        super(SkillId.Wall_Punch, AnimationType.RANGED);


    }

    @Override
    public void affect(Entity target)

    {
        if (target.getEntityType() == EntityType.Wall) {
            if (target.getLocation().GridX > 0 && target.getLocation().GridX < Settings.get().tileMapWidth - 1 && target.getLocation().GridY > 0 && target.getLocation().GridY < Settings.get().tileMapHeight - 1) {
                target.setInactive();

            }

        }

    }

}
