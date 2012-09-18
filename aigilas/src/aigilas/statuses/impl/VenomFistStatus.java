package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class VenomFistStatus extends BaseStatus {
    public VenomFistStatus(BaseCreature target)

    {
        super(target);

        add(Status.Poison, StatusComponent.Contagion);
    }
}
