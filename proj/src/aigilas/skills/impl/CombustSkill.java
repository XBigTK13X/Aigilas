package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;
import spx.entities.EntityManager;
import spx.entities.IActor;

import java.util.List;

public class CombustSkill extends ISkill {
    private static final int CombustDistance = 1;

    public CombustSkill()

    {
        super(SkillId.COMBUST, AnimationType.RANGED);

        add(Elements.AIR, Elements.PHYSICAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(10, _source);
        if (!target.isActive()) {
            List<IActor> targets = EntityManager.getActorsSurrounding(target.getLocation(), CombustDistance);
            for (int ii = 0; ii < targets.size(); ii++) {
                StatusFactory.apply((ICreature) targets.get(ii), Status.Burn);

            }

        }

    }

}
