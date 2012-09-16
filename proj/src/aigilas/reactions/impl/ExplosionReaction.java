package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;
import spx.entities.EntityManager;
import spx.entities.IActor;

public class ExplosionReaction implements IReaction {
    @Override
    public void affect(ICreature target) {
        for (IActor creature : EntityManager.getActorsSurrounding(target.getLocation(), 2)) {
            ((ICreature) creature).applyDamage(10);
        }
    }
}
