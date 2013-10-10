package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.statuses.BaseStatus;

public class SoakingWetStatus extends BaseStatus {
    public SoakingWetStatus(BaseCreature target) {
        super(target);
        _blockedElements.add(Elements.Fire);
    }
}
