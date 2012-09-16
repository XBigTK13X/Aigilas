package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class BurnStatus extends IStatus {
    public BurnStatus(ICreature target)

    {
        super(target);

        _prevents.add(CreatureAction.Movement);
    }

    @Override
    public void update() {
        super.update();
        _target.applyDamage(1.0f);
    }
}
