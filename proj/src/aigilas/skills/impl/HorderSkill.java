package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class HorderSkill extends BaseSkill {
    public HorderSkill()

    {
        super(SkillId.HORDER, AnimationType.SELF);

        add(Elements.MENTAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        StatusFactory.apply(source, Status.Hord);

    }

}
