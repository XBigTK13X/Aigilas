package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AcidNozzleSkill extends BaseSkill {
    public AcidNozzleSkill()

    {
        super(SkillId.ACID_NOZZLE, AnimationType.STATIONARY);

        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        CreatureFactory.createMinion(_implementationId, source);

    }

}
