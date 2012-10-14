package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;

public class HordStatus extends BaseStatus {
    public HordStatus(BaseCreature target) {
        super(target);
        _buff = new StatBuff(StatType.Strength, target.getInventoryCount());
        setup();
    }
}
