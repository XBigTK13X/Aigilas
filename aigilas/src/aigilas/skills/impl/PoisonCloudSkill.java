package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class PoisonCloudSkill extends BaseSkill {
    public PoisonCloudSkill()

    {
        super(SkillId.POISON_CLOUD, AnimationType.CLOUD);


    }

    @Override
    public void affect(BaseCreature target)

    {
        StatusFactory.apply(target, Status.Poison);

    }

}
