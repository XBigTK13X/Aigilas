package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;

public class PoisonStatus extends BaseStatus {
    public PoisonStatus(BaseCreature target) {
        super(target);
    }

    @Override
    public void update() {
        super.update();
        _target.applyDamage(5);
    }
}
