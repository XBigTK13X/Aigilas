package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class MimicSkill extends BaseSkill {
    public MimicSkill()

    {
        super(SkillId.Mimic, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {

    }

}
