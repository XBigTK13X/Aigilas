package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.reactions.IReaction;

public class FastForwardReaction implements IReaction {
	@Override
	public void Affect(ICreature target)

	{
		target.ApplyDamage(-1, null, false, StatType.AGE);
	}
}
