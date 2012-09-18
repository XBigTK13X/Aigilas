package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.statuses.BaseStatus;

public class PreventMentalUsageStatus extends BaseStatus {
    public PreventMentalUsageStatus(BaseCreature target)

    {
        super(target);

        _blockedElements.add(Elements.MENTAL);
    }
}
