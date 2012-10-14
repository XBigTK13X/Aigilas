package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.statuses.BaseStatus;

public class PreventLightUsageStatus extends BaseStatus {
    public PreventLightUsageStatus(BaseCreature target)

    {
        super(target);

        _blockedElements.add(Elements.Light);
    }
}
