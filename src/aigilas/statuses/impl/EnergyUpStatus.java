package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class EnergyUpStatus extends BaseStatus {
    public EnergyUpStatus(BaseCreature target) {
        super(target, Status.EnergyUp);
        _buff = new StatBuff(StatType.Energy, impl.Info.Magnitude);
        setup();
    }
}
