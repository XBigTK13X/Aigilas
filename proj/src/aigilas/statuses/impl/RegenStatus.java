package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class RegenStatus extends IStatus {
    public RegenStatus(ICreature target) {
        super(target);
    }

    @Override
    public void Update() {

        super.Update();
        _target.ApplyDamage(-1f);
    }
}
