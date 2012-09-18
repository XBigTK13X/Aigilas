package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class BurnOneHitStatus extends BaseStatus {
    public BurnOneHitStatus(BaseCreature target)

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
