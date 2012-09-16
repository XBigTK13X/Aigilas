package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;

public class RustReaction implements IReaction {
    @Override
    public void Affect(ICreature target)

    {
        target.DestroyRandomItemFromInventory();
    }
}
