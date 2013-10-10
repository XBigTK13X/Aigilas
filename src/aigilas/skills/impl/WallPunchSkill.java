package aigilas.skills.impl;

import aigilas.Aigilas;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.EntityTypes;
import sps.core.SpsConfig;
import sps.entities.Entity;

public class WallPunchSkill extends BaseSkill {
    public WallPunchSkill() {
        super(SkillId.Wall_Punch, AnimationType.RANGED);
    }

    @Override
    public void affect(Entity target) {
        if (target.getEntityType() == EntityTypes.get(Aigilas.Entities.Wall)) {
            if (target.getLocation().GridX > 0 && target.getLocation().GridX < SpsConfig.get().tileMapWidth - 1 && target.getLocation().GridY > 0 && target.getLocation().GridY < SpsConfig.get().tileMapHeight - 1) {
                target.setInactive();

            }

        }

    }

}
