package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.reactions.IReaction;

public class FastForwardReaction implements IReaction {
    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(-1, null, false, StatType.AGE);
    }
}
