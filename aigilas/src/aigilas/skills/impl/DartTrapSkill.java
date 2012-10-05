package aigilas.skills.impl;

import aigilas.creatures.impl.CreatureFactory;
import aigilas.entities.SkillEffect;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.entities.Entity;

public class DartTrapSkill extends BaseSkill {
    public DartTrapSkill()

    {
        super(SkillId.DART_TRAP, AnimationType.RANGED);


    }

    @Override
    public void cleanup(Entity target, SkillEffect source)

    {
        CreatureFactory.createMinion(_id, _source, source, target.getLocation());

    }

}
