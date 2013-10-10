package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class BoilSkill extends BaseSkill {
    public BoilSkill() {
        super(SkillId.Boil, AnimationType.SELF);
    }

    @Override
    public void activate(BaseCreature source) {
        StatusFactory.apply(source, Status.Boil);
    }
}
