package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class VenomFistSkill extends BaseSkill {
    public VenomFistSkill()

    {
        super(SkillId.Venom_Fist, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {

        StatusFactory.apply(source, Status.VenomFist);

    }

}
