package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class PlagueSkill extends BaseSkill {
    public PlagueSkill()

    {
        super(SkillId.Strength_Up, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {

        StatusFactory.apply(source, Status.Toxic);

    }

}
