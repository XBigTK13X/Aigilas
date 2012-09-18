package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class ConfusionSkill extends BaseSkill {
    public ConfusionSkill()

    {
        super(SkillId.CONFUSION, AnimationType.RANGED);

        add(Elements.MENTAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        StatusFactory.apply(target, Status.Confusion);

    }

}
