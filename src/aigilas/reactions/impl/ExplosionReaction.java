package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.BaseReaction;
import aigilas.reactions.Reaction;
import sps.entities.EntityManager;
import sps.entities.IActor;

public class ExplosionReaction extends BaseReaction {

    public ExplosionReaction() {
        super(Reaction.Explosion);
    }

    @Override
    public void affect(BaseCreature target) {
        for (IActor creature : EntityManager.get().getActorsSurrounding(target.getLocation(), 2)) {
            ((BaseCreature) creature).applyDamage(reactionImpl.Info.Magnitude);
        }
    }
}
