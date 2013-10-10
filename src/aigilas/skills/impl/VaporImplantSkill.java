package aigilas.skills.impl;

import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.entities.Entity;

public class VaporImplantSkill extends BaseSkill {
    public VaporImplantSkill()

    {
        super(SkillId.Vapor_Implant, AnimationType.RANGED);


    }

    @Override
    public void affect(Entity target)

    {
        CreatureFactory.createMinion(SkillId.Vapor_Cloud, _source, null, target.getLocation());

    }

}
