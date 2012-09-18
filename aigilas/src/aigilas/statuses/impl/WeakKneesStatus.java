package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureAction;
import aigilas.statuses.BaseStatus;

public class WeakKneesStatus extends BaseStatus {
    public WeakKneesStatus(BaseCreature target)

    {
        super(target);

        _prevents.add(CreatureAction.Movement);
        _prevents.add(CreatureAction.Attacking);
    }
}
