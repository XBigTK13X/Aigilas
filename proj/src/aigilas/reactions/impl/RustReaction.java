package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;

public class RustReaction implements IReaction {
    @Override
    public void affect(ICreature target)

    {
        target.destroyRandomItemFromInventory();
    }
}
