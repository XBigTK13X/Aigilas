package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class PreventRegenerationStatus extends IStatus {
    public PreventRegenerationStatus(ICreature target)

    {
        super(target);

        _prevents.add(CreatureAction.Regeneration);
    }
}
