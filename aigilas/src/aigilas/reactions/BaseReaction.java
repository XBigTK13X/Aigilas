package aigilas.reactions;

import aigilas.creatures.BaseCreature;

public class BaseReaction {

    protected Reaction reactionImpl;

    public BaseReaction() {
    }

    ;

    public BaseReaction(Reaction reactionImpl) {
        this.reactionImpl = reactionImpl;
    }

    public void affect(BaseCreature target) {

    }
}
