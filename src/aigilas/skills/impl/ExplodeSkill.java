package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class ExplodeSkill extends BaseSkill {
    public ExplodeSkill()

    {
        super(SkillId.Explode, AnimationType.CLOUD);


    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, _source, true);

    }

}
