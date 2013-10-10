package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class IceShardSkill extends BaseSkill {
    public IceShardSkill()

    {
        super(SkillId.Ice_Shard, AnimationType.CLOUD);


    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, _source, true);

    }

}
