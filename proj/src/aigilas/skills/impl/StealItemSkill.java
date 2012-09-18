package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.items.ItemFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.core.RNG;

public class StealItemSkill extends BaseSkill {
    public StealItemSkill()

    {
        super(SkillId.STEAL_ITEM, AnimationType.ROTATE);

        add(Elements.WATER, Elements.AIR);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        if (RNG.Rand.nextInt(100) > 0) {
            _source.pickupItem(ItemFactory.createRandomPlain());

        }

    }

}
