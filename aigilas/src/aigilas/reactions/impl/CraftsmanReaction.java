package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.items.ItemFactory;
import aigilas.reactions.BaseReaction;

public class CraftsmanReaction extends BaseReaction {
    @Override
    public void affect(BaseCreature target) {
        ItemFactory.createRandomPlain(target.getLocation());
    }
}
