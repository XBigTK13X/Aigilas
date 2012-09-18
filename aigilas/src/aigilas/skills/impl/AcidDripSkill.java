package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AcidDripSkill extends BaseSkill {
    public AcidDripSkill()

    {
        super(SkillId.ACID_DRIP, AnimationType.STATIONARY);

        StartOffCenter = true;
        addCost(StatType.MANA, 10);
        add(Elements.WATER);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(20, _source);

    }

}
