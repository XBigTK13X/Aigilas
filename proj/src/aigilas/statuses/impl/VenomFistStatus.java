package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class VenomFistStatus extends IStatus {
    public VenomFistStatus(ICreature target)

    {
        super(target);

        add(Status.Poison, StatusComponent.Contagion);
    }
}
