package aigilas.skills.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.EntityType;
import sps.core.Settings;
import sps.entities.IEntity;

public class WallPunchSkill extends BaseSkill {
    public WallPunchSkill()

    {
        super(SkillId.WALL_PUNCH, AnimationType.RANGED);

        add(Elements.EARTH);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(IEntity target)

    {
        if (target.getEntityType() == EntityType.WALL) {
            if (target.getLocation().GridX > 0 && target.getLocation().GridX < Settings.get().tileMapWidth - 1 && target.getLocation().GridY > 0 && target.getLocation().GridY < Settings.get().tileMapHeight - 1) {
                target.setInactive();

            }

        }

    }

}
