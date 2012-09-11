package aigilas.statuses.impl;

import spx.core.RNG;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class RandomBuffStatus extends IStatus {
	public RandomBuffStatus(ICreature target)

	{
		super(target);
		_buff = new StatBuff(StatType.values()[RNG.Next(0, 3)], 10);
		Setup();
	}
}
