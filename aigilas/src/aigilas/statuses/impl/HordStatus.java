package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class HordStatus extends BaseStatus {
    public HordStatus(BaseCreature target) {
        super(target, Status.Hord);
        _buff = new StatBuff(StatType.Strength, target.getInventoryCount() * impl.Info.Magnitude);
        setup();
    }
}
