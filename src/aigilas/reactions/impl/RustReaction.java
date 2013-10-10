package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.reactions.BaseReaction;

public class RustReaction extends BaseReaction {
    @Override
    public void affect(BaseCreature target) {
        target.destroyRandomItemFromInventory();
    }
}
