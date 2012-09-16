package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class MimicSkill extends ISkill {
    public MimicSkill()

    {
        super(SkillId.MIMIC, AnimationType.RANGED);

        Add(Elements.DARK);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {

    }

}
