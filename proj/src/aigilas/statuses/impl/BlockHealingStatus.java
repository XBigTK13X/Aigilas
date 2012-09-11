package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class BlockHealingStatus extends IStatus {
	public BlockHealingStatus(ICreature target)

	{
		super(target);

		_prevents.add(CreatureAction.ReceiveHealing);
	}
}
