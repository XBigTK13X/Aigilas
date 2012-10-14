package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;
import sps.entities.EntityManager;
import sps.entities.IActor;

import java.util.List;

public class CombustSkill extends BaseSkill {
    private static final int CombustDistance = 1;

    public CombustSkill()

    {
        super(SkillId.Combust, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, _source);
        if (!target.isActive()) {
            List<IActor> targets = EntityManager.get().getActorsSurrounding(target.getLocation(), CombustDistance);
            for (IActor target1 : targets) {
                StatusFactory.apply((BaseCreature) target1, Status.Burn);

            }

        }

    }

}
