package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;
import sps.entities.EntityManager;

public class BreakingWheelSkill extends BaseSkill {
    public BreakingWheelSkill()

    {
        super(SkillId.BREAKING_WHEEL, AnimationType.SELF);

        add(Elements.DARK);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        CreatureFactory.create(ActorType.BREAKING_WHEEL, EntityManager.get().getEmptyLocation());

    }

}
