package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class PoisonStatus extends IStatus {
    public PoisonStatus(ICreature target) {
        super(target);
    }

    @Override
    public void Update() {
        super.Update();
        _target.ApplyDamage(2.1f);
    }
}
