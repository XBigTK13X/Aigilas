package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.reactions.IReaction;

public class MindBlownReaction implements IReaction {
    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(10, null, true, StatType.MANA);
    }
}
