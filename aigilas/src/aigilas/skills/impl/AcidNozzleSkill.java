package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AcidNozzleSkill extends BaseSkill {
    public AcidNozzleSkill() {
        super(SkillId.Acid_Nozzle, AnimationType.STATIONARY);
    }

    @Override
    public void activate(BaseCreature source) {
        CreatureFactory.createMinion(_id, source);
    }
}
