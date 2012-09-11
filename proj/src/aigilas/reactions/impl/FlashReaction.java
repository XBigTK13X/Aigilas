package aigilas.reactions.impl;

import spx.entities.EntityManager;
import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;

public class FlashReaction implements IReaction {
	@Override
	public void Affect(ICreature target)

	{
		target.SetLocation(EntityManager.GetEmptyLocation());
	}
}
