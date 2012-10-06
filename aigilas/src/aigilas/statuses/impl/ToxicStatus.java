package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.SkillId;
import aigilas.statuses.BaseStatus;

public class ToxicStatus extends BaseStatus {
    public ToxicStatus(BaseCreature target)

    {
        super(target);

        _target = target;
    }

    @Override
    public void act() {
        try {
            CreatureFactory.createMinion(SkillId.PLAGUE, _target, null, _target.getLocation());
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }
}
