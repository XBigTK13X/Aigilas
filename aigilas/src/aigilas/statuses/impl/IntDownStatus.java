package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class IntDownStatus extends BaseStatus {
    public IntDownStatus(BaseCreature target) {
        super(target, Status.IntDown);
        _buff = new StatBuff(StatType.Wisdom, -impl.Info.Magnitude);
        setup();
    }
}
