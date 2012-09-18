package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;

public class ZapStatus extends BaseStatus {
    public ZapStatus(BaseCreature target)

    {
        super(target);
    }

    @Override
    public void setup() {
        super.setup();
        _target.applyDamage(10);
        _isActive = false;
    }
}
