package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class SlowDownStatus extends BaseStatus {
    public SlowDownStatus(BaseCreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.Move_Cool_Down, -100);
        _buffMax = true;
        setup();
    }
}
