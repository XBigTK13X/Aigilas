package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class AcidNozzleSkill extends ISkill {
    public AcidNozzleSkill()

    {
        super(SkillId.ACID_NOZZLE, AnimationType.STATIONARY);

        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        CreatureFactory.createMinion(_implementationId, source);

    }

}
