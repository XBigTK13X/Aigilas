package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class ColdShoulderSkill extends BaseSkill {
    public ColdShoulderSkill()

    {
        super(SkillId.Cold_Shoulder, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {

        applyToPlayers(Status.ColdShoulder);

    }

}
