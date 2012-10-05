package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.items.GenericItem;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class ThrowItemSkill extends BaseSkill {
    private float _itemStrength = 0;

    public ThrowItemSkill()

    {
        super(SkillId.THROW_ITEM, AnimationType.RANGED);


    }

    @Override
    public void activate(BaseCreature source) {
        GenericItem item = source.destroyRandomItemFromInventory();
        if (item != null) {
            _itemStrength = item.Modifiers.getSum() * 3;
            super.activate(source);

        }

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(_itemStrength, _source);

    }

}
