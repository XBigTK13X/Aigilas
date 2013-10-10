package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class SlowDownStatus extends BaseStatus {
    public SlowDownStatus(BaseCreature target) {
        super(target, Status.SlowDown);

        _buff = new StatBuff(StatType.Move_Cool_Down, -impl.Info.Magnitude);
        _buffMax = true;
        setup();
    }
}
