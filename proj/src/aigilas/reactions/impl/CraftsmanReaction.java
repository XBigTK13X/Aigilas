package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.items.ItemFactory;
import aigilas.reactions.IReaction;

public class CraftsmanReaction implements IReaction {
    @Override
    public void affect(ICreature target)

    {
        try {
            ItemFactory.createRandomPlain(target.getLocation());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
