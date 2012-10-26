package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class SpeedUpStatus extends BaseStatus {
    public SpeedUpStatus(BaseCreature target) {
        super(target, Status.SpeedUp);
        _buff = new StatBuff(StatType.Move_Cool_Down, impl.Info.Magnitude);
        setup();
    }
}
