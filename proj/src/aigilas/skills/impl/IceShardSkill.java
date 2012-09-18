package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class IceShardSkill extends BaseSkill {
    public IceShardSkill()

    {
        super(SkillId.ICE_SHARD, AnimationType.CLOUD);

        add(Elements.WATER);
        addCost(StatType.MANA, 0);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, _source, true);

    }

}
