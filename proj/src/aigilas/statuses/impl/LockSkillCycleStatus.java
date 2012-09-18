package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;

public class LockSkillCycleStatus extends BaseStatus {
    public LockSkillCycleStatus(BaseCreature target)

    {
        super(target);

        _prevents.add(CreatureAction.SkillCycle);
    }
}
