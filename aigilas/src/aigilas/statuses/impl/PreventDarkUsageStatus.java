package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.statuses.BaseStatus;

public class PreventDarkUsageStatus extends BaseStatus {
    public PreventDarkUsageStatus(BaseCreature target)

    {
        super(target);

        _blockedElements.add(Elements.DARK);
    }
}
