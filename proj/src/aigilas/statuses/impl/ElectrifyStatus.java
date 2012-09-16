package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class ElectrifyStatus extends IStatus {
    public ElectrifyStatus(ICreature target)

    {
        super(target);

        add(Status.Zap, StatusComponent.Passive);
    }
}
