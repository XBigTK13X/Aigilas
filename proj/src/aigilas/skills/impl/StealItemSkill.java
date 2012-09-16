package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.items.ItemFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.core.RNG;

public class StealItemSkill extends ISkill {
    public StealItemSkill()

    {
        super(SkillId.STEAL_ITEM, AnimationType.ROTATE);

        Add(Elements.WATER, Elements.AIR);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {
        if (RNG.Rand.nextInt(100) > 0) {
            _source.PickupItem(ItemFactory.CreateRandomPlain());

        }

    }

}
