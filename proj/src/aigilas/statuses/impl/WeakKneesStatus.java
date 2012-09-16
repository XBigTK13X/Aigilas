package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class WeakKneesStatus extends IStatus {
    public WeakKneesStatus(ICreature target)

    {
        super(target);

        _prevents.add(CreatureAction.Movement);
        _prevents.add(CreatureAction.Attacking);
    }
}
