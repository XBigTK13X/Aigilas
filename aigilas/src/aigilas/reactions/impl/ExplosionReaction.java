package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.IReaction;
import sps.entities.EntityManager;
import sps.entities.IActor;

public class ExplosionReaction implements IReaction {
    @Override
    public void affect(BaseCreature target) {
        for (IActor creature : EntityManager.get().getActorsSurrounding(target.getLocation(), 2)) {
            ((BaseCreature) creature).applyDamage(10);
        }
    }
}
