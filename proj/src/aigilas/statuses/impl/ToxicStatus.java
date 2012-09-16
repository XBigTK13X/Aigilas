package aigilas.statuses.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.skills.SkillId;
import aigilas.statuses.IStatus;

public class ToxicStatus extends IStatus {
    public ToxicStatus(ICreature target)

    {
        super(target);

        _target = target;
    }

    @Override
    public void Act() {
        try {
            CreatureFactory.CreateMinion(SkillId.PLAGUE, _target, null, _target.GetLocation());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
