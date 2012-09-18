package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class StrengthUpStatus extends BaseStatus {
    public StrengthUpStatus(BaseCreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.STRENGTH, 10f);
        setup();
    }
}
