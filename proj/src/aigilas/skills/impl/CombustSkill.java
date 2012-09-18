package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;
import spx.entities.EntityManager;
import spx.entities.IActor;

import java.util.List;

public class CombustSkill extends BaseSkill {
    private static final int CombustDistance = 1;

    public CombustSkill()

    {
        super(SkillId.COMBUST, AnimationType.RANGED);

        add(Elements.AIR, Elements.PHYSICAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, _source);
        if (!target.isActive()) {
            List<IActor> targets = EntityManager.getActorsSurrounding(target.getLocation(), CombustDistance);
            for (IActor target1 : targets) {
                StatusFactory.apply((BaseCreature) target1, Status.Burn);

            }

        }

    }

}
