package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class IntDownStatus extends BaseStatus {
    public IntDownStatus(BaseCreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.Wisdom, -10);
        setup();
    }
}
