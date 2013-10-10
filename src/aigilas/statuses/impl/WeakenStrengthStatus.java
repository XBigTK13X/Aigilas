package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class WeakenStrengthStatus extends BaseStatus {
    public WeakenStrengthStatus(BaseCreature target) {
        super(target, Status.WeakenStrength);
        _buff = new StatBuff(StatType.Strength, -impl.Info.Magnitude);
        setup();
    }
}
