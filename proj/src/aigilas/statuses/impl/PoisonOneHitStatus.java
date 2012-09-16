package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class PoisonOneHitStatus extends IStatus {
    public PoisonOneHitStatus(ICreature target)

    {
        super(target);

        add(Status.Poison, StatusComponent.Contagion);
    }

    @Override
    public void update() {
        super.update();
        if (_wasPassed) {
            _isActive = false;
        }
    }
}
