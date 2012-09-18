package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.reactions.IReaction;

public class MindBlownReaction implements IReaction {
    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, null, true, StatType.MANA);
    }
}
