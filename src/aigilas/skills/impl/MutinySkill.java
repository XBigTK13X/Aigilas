package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class MutinySkill extends BaseSkill {
    public MutinySkill() {
        super(SkillId.Mutiny, AnimationType.RANGED);
    }

    @Override
    public void affect(BaseCreature target) {
        StatusFactory.apply(target, Status.Mutiny);
    }
}
