package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureAction;
import aigilas.statuses.BaseStatus;

public class BlindStatus extends BaseStatus {
    public BlindStatus(BaseCreature target)

    {
        super(target);
        _prevents.add(CreatureAction.WontHitNonTargets);
    }
}
