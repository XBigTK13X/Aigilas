package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class ElectrifyStatus extends BaseStatus {
    public ElectrifyStatus(BaseCreature target) {
        super(target);
        add(Status.Zap, StatusComponent.Passive);
    }
}
