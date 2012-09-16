package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;
import spx.entities.EntityManager;

public class FlashReaction implements IReaction {
    @Override
    public void affect(ICreature target)

    {
        target.setLocation(EntityManager.getEmptyLocation());
    }
}
