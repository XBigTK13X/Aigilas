package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class CavalrySkill extends BaseSkill {
    public CavalrySkill()
    {
        super(SkillId.Cavalry, AnimationType.SELF);
    }

    @Override
    public void activate(BaseCreature source) {
        applyToPlayers(Status.DefenseUp);
    }
}
