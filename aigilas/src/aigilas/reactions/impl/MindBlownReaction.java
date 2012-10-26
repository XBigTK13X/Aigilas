package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.reactions.BaseReaction;
import aigilas.reactions.Reaction;

public class MindBlownReaction extends BaseReaction {
    public MindBlownReaction() {
        super(Reaction.Mind_Blown);
    }

    @Override
    public void affect(BaseCreature target) {
        target.applyDamage(reactionImpl.Info.Magnitude, null, true, StatType.Mana);
    }
}
