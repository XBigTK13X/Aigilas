package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.entities.EntityManager;

public class HypothermiaSkill extends BaseSkill {
    public HypothermiaSkill()

    {
        super(SkillId.Dismemberment, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature target)

    {
        for (int ii = 0; ii < 4; ii++) {
            CreatureFactory.createMinion(SkillId.Ice_Shard, target, _behavior.getGraphic(), EntityManager.get().getEmptyLocation());

        }

    }

}
