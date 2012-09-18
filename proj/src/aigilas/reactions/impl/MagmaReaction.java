package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.IReaction;

public class MagmaReaction implements IReaction {
    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(30f);
    }
}
