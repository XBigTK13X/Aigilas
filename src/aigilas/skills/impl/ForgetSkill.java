package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class ForgetSkill extends BaseSkill

{
    public ForgetSkill()

    {
        super(SkillId.Forget_Skill, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {

        _source.removeLeastUsedSkill();

    }

}
