package aigilas.reactions.impl;

import aigilas.creatures.BaseCreature;
import aigilas.items.ItemFactory;
import aigilas.reactions.IReaction;

public class CraftsmanReaction implements IReaction {
    @Override
    public void affect(BaseCreature target)

    {
        try {
            ItemFactory.createRandomPlain(target.getLocation());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
