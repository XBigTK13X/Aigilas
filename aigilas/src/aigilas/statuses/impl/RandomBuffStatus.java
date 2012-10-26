package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import sps.core.RNG;

public class RandomBuffStatus extends BaseStatus {
    public RandomBuffStatus(BaseCreature target) {
        super(target, Status.RandomBuff);
        _buff = new StatBuff(StatType.values()[RNG.next(0, 4)], impl.Info.Magnitude);
        setup();
    }
}
