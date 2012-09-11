package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class ZapStatus extends IStatus {
	public ZapStatus(ICreature target)

	{
		super(target);
	}

	@Override
	public void Setup() {
		super.Setup();
		_target.ApplyDamage(10);
		_isActive = false;
	}
}
