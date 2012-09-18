package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.IReaction;
import sps.entities.EntityManager;

public class FlashReaction implements IReaction {
    @Override
    public void affect(BaseCreature target)

    {
        target.setLocation(EntityManager.get().getEmptyLocation());
    }
}
