package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.SkillId;
import aigilas.statuses.BaseStatus;

public class ToxicStatus extends BaseStatus {
    public ToxicStatus(BaseCreature target) {
        super(target);
        _target = target;
    }

    @Override
    public void act() {
        CreatureFactory.createMinion(SkillId.Plague, _target, null, _target.getLocation());
    }
}
