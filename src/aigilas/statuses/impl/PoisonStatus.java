package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class PoisonStatus extends BaseStatus {
    public PoisonStatus(BaseCreature target) {
        super(target, Status.Poison);
    }

    @Override
    public void update() {
        super.update();
        _target.applyDamage(impl.Info.Magnitude);
    }
}
