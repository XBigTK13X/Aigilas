package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class HordStatus extends IStatus {
    public HordStatus(ICreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.STRENGTH, target.GetInventoryCount());
        Setup();
    }
}
