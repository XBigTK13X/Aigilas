package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class SpeedUpStatus extends IStatus {
	public SpeedUpStatus(ICreature target)

	{
		super(target);
		_buff = new StatBuff(StatType.MOVE_COOL_DOWN, 5f);
		Setup();
	}
}
