package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class LobotomyReaction implements IReaction {
    @Override
    public void affect(ICreature target)

    {
        StatusFactory.apply(target, Status.IntDown);
    }
}
