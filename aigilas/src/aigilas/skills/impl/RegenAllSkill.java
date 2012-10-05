package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class RegenAllSkill extends BaseSkill {
    public RegenAllSkill()

    {
        super(SkillId.REGEN_ALL, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        applyToPlayers(Status.Regen);

    }

}
