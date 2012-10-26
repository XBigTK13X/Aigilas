package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class RegenStatus extends BaseStatus {
    public RegenStatus(BaseCreature target) {
        super(target, Status.Regen);
    }

    @Override
    public void update() {
        super.update();
        _target.applyDamage(-impl.Info.Magnitude);
    }
}
