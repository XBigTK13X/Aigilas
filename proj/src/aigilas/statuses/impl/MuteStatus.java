package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureAction;
import aigilas.statuses.BaseStatus;

public class MuteStatus extends BaseStatus {
    public MuteStatus(BaseCreature target)

    {
        super(target);

        _prevents.add(CreatureAction.SkillUsage);
    }
}
