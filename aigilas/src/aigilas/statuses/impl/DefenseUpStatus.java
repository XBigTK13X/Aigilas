package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class DefenseUpStatus extends BaseStatus {
    public DefenseUpStatus(BaseCreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.Defense, 10);
        setup();
    }
}
