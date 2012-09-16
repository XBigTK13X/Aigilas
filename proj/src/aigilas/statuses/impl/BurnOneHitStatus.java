package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class BurnOneHitStatus extends IStatus {
    public BurnOneHitStatus(ICreature target)

    {
        super(target);

        add(Status.Burn, StatusComponent.Contagion);
    }

    @Override
    public void update() {
        super.update();
        if (_wasPassed) {
            _isActive = false;
        }
    }
}
