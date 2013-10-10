package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class PoisonOneHitStatus extends BaseStatus {
    public PoisonOneHitStatus(BaseCreature target) {
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
