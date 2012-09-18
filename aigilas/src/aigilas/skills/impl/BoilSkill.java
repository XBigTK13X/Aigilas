package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class BoilSkill extends BaseSkill {
    public BoilSkill()

    {
        super(SkillId.BOIL, AnimationType.SELF);

        add(Elements.AIR);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        StatusFactory.apply(source, Status.Boil);

    }

}
