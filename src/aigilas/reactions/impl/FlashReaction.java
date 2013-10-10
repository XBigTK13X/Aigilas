package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.BaseReaction;
import sps.entities.EntityManager;

public class FlashReaction extends BaseReaction {
    @Override
    public void affect(BaseCreature target) {
        target.setLocation(EntityManager.get().getEmptyLocation());
    }
}
