package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class ColdShoulderSkill extends BaseSkill {
    public ColdShoulderSkill()

    {
        super(SkillId.COLD_SHOULDER, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        applyToPlayers(Status.ColdShoulder);

    }

}
