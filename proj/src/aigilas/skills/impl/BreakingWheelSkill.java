package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;
import spx.entities.EntityManager;

public class BreakingWheelSkill extends ISkill {
    public BreakingWheelSkill()

    {
        super(SkillId.BREAKING_WHEEL, AnimationType.SELF);

        add(Elements.DARK);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        CreatureFactory.create(ActorType.BREAKING_WHEEL, EntityManager.getEmptyLocation());

    }

}
