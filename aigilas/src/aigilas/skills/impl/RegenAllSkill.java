package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class RegenAllSkill extends BaseSkill {
    public RegenAllSkill()

    {
        super(SkillId.Regen_All, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {

        applyToPlayers(Status.Regen);

    }

}
