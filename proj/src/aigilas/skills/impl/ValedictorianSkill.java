package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ValedictorianSkill extends ISkill {
    public ValedictorianSkill()

    {
        super(SkillId.VALEDICTORIAN, AnimationType.RANGED);

        Add(Elements.MENTAL, Elements.LIGHT);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {

    }

}
