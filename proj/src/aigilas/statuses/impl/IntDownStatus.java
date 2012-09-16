package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class IntDownStatus extends IStatus {
    public IntDownStatus(ICreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.WISDOM, -10);
        Setup();
    }
}
