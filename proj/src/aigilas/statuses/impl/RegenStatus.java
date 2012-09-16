package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class RegenStatus extends IStatus {
    public RegenStatus(ICreature target) {
        super(target);
    }

    @Override
    public void update() {

        super.update();
        _target.applyDamage(-1f);
    }
}
