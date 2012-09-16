package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class MutinySkill extends ISkill {
    public MutinySkill()

    {
        super(SkillId.MUTINY, AnimationType.RANGED);

        Add(Elements.MENTAL, Elements.PHYSICAL);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {
        StatusFactory.Apply(target, Status.Mutiny);

    }

}
