package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.IReaction;

public class RustReaction implements IReaction {
    @Override
    public void affect(BaseCreature target)

    {
        target.destroyRandomItemFromInventory();
    }
}
