package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.items.ItemFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.core.RNG;

//TODO This is a debugging stub
public class StealItemSkill extends BaseSkill {
    public StealItemSkill() {
        super(SkillId.Steal_Item, AnimationType.ROTATE);
    }

    @Override
    public void affect(BaseCreature target) {
        if (RNG.next(0, 100) > 0) {
            _source.pickupItem(ItemFactory.createRandomPlain());
        }
    }

}
