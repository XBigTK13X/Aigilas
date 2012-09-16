package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;

public class MagmaReaction implements IReaction {
    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(30f);
    }
}
