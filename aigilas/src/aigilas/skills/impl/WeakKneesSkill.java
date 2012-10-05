package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class WeakKneesSkill extends BaseSkill {
    public WeakKneesSkill()

    {
        super(SkillId.WEAK_KNEES, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {
        StatusFactory.apply(target, Status.WeakKnees);

    }

}
