package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class DartSkill extends BaseSkill {
    public DartSkill()

    {
        super(SkillId.Dart, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {
        StatusFactory.apply(target, Status.Poison);
        target.applyDamage(5, _source);

    }

}
