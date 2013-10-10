package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class StrengthUpStatus extends BaseStatus {
    public StrengthUpStatus(BaseCreature target) {
        super(target, Status.StrengthUp);
        _buff = new StatBuff(StatType.Strength, impl.Info.Magnitude);
        setup();
    }
}
