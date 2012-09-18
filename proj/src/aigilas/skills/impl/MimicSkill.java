package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class MimicSkill extends BaseSkill {
    public MimicSkill()

    {
        super(SkillId.MIMIC, AnimationType.RANGED);

        add(Elements.DARK);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {

    }

}
