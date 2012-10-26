package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.reactions.BaseReaction;
import aigilas.reactions.Reaction;

public class FastForwardReaction extends BaseReaction {
    public FastForwardReaction() {
        super(Reaction.Fast_Forward);
    }

    @Override
    public void affect(BaseCreature target) {
        target.applyDamage(-reactionImpl.Info.Magnitude, null, false, StatType.Age);
    }
}
