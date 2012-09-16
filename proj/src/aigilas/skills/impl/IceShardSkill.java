package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class IceShardSkill extends ISkill {
    public IceShardSkill()

    {
        super(SkillId.ICE_SHARD, AnimationType.CLOUD);

        add(Elements.WATER);
        addCost(StatType.MANA, 0);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(10, _source, true);

    }

}
