package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureAction;
import aigilas.statuses.BaseStatus;

public class PreventRegenerationStatus extends BaseStatus {
    public PreventRegenerationStatus(BaseCreature target) {
        super(target);
        _prevents.add(CreatureAction.Regeneration);
    }
}
