package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.entities.Elements;
import aigilas.statuses.IStatus;

public class SoakingWetStatus extends IStatus {
	public SoakingWetStatus(ICreature target)

	{
		super(target);

		_blockedElements.add(Elements.FIRE);
	}
}
