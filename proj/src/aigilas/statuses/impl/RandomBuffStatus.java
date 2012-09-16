package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;
import spx.core.RNG;

public class RandomBuffStatus extends IStatus {
    public RandomBuffStatus(ICreature target)

    {
        super(target);
        _buff = new StatBuff(StatType.values()[RNG.next(0, 3)], 10);
        setup();
    }
}
