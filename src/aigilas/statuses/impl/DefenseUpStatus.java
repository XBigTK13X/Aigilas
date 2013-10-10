package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class DefenseUpStatus extends BaseStatus {
    public DefenseUpStatus(BaseCreature target) {
        super(target, Status.DefenseUp);
        _buff = new StatBuff(StatType.Defense, impl.Info.Magnitude);
        setup();
    }
}
