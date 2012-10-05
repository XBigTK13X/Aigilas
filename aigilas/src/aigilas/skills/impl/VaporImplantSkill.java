package aigilas.skills.impl;

import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.entities.IEntity;

public class VaporImplantSkill extends BaseSkill {
    public VaporImplantSkill()

    {
        super(SkillId.VAPOR_IMPLANT, AnimationType.RANGED);


    }

    @Override
    public void affect(IEntity target)

    {
        CreatureFactory.createMinion(SkillId.VAPOR_CLOUD, _source, null, target.getLocation());

    }

}
