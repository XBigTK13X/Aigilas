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
    public void Update() {
        super.Update();
        _target.ApplyDamage(1.0f);
    }
}
