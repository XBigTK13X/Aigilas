package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.items.GenericItem;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ThrowItemSkill extends ISkill {
    private float _itemStrength = 0;

    public ThrowItemSkill()

    {
        super(SkillId.THROW_ITEM, AnimationType.RANGED);

        add(Elements.AIR);
        addCost(StatType.MANA, 0);

    }

    @Override
    public void activate(ICreature source) {
        GenericItem item = source.destroyRandomItemFromInventory();
        if (item != null) {
            _itemStrength = item.Modifers.getSum() * 3;
            super.activate(source);

        }

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(_itemStrength, _source);

    }

}
