package aigilas.skills.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.bridge.EntityType;
import spx.core.Settings;
import spx.entities.IEntity;

public class WallPunchSkill extends ISkill {
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
