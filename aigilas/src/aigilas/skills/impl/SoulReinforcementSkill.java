package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class SoulReinforcementSkill extends BaseSkill {
    public SoulReinforcementSkill()

    {
        super(SkillId.SOUL_REINFORCEMENT, AnimationType.SELF);

        add(Elements.LIGHT);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        StatusFactory.apply(source, Status.Berserk);

    }

}
