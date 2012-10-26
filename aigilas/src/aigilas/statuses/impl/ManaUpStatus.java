package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class ManaUpStatus extends BaseStatus {
    public ManaUpStatus(BaseCreature target) {
        super(target, Status.ManaUp);
        _buff = new StatBuff(StatType.Mana, impl.Info.Magnitude);
        setup();
    }
}
