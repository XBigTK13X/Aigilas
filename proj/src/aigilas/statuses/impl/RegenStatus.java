package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;

public class RegenStatus extends BaseStatus {
    public RegenStatus(BaseCreature target) {
        super(target);
    }

    @Override
    public void update() {

        super.update();
        _target.applyDamage(-1f);
    }
}
