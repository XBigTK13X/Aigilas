package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class BerserkStatus extends BaseStatus {
    public BerserkStatus(BaseCreature target)

    {
        super(target);
        add(Status.RandomBuff, StatusComponent.KillReward);
    }
}
