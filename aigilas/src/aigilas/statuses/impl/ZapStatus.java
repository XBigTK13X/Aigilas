package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;

public class ZapStatus extends BaseStatus {
    public ZapStatus(BaseCreature target) {
        super(target, Status.Zap);
    }

    @Override
    public void setup() {
        super.setup();
        _target.applyDamage(impl.Info.Magnitude);
        _isActive = false;
    }
}
