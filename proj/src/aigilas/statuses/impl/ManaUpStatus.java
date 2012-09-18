package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class ManaUpStatus extends BaseStatus {
    public ManaUpStatus(BaseCreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.MANA, 20f);
        setup();
    }
}
