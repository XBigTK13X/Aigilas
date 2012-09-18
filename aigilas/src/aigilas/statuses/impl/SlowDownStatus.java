package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class SlowDownStatus extends BaseStatus {
    public SlowDownStatus(BaseCreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.MOVE_COOL_DOWN, 10);
        _buffMax = true;
        setup();
    }
}
