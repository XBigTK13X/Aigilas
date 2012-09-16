package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class PoisonStatus extends IStatus {
    public PoisonStatus(ICreature target) {
        super(target);
    }

    @Override
    public void update() {
        super.update();
        _target.applyDamage(2.1f);
    }
}
