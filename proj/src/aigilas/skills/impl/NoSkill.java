package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import spx.entities.IEntity;

public class NoSkill extends BaseSkill {
    public NoSkill()

    {
        super(SkillId.NO_SKILL, AnimationType.NONE);

    }

    @Override
    public void activate(BaseCreature source) {

    }

    @Override
    public void affect(IEntity target)

    {

    }

    @Override
    public void affect(BaseCreature target)

    {

    }

}
