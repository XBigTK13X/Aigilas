package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.BaseReaction;
import aigilas.reactions.Reaction;

public class MagmaReaction extends BaseReaction {
    public MagmaReaction() {
        super(Reaction.Magma);
    }

    @Override
    public void affect(BaseCreature target) {
        target.applyDamage(reactionImpl.Info.Magnitude);
    }
}
