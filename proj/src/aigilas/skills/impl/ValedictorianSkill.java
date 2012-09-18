package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class ValedictorianSkill extends BaseSkill {
    public ValedictorianSkill()

    {
        super(SkillId.VALEDICTORIAN, AnimationType.RANGED);

        add(Elements.MENTAL, Elements.LIGHT);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {

    }

}
