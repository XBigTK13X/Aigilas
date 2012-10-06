package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.entities.Entity;

public class NoSkill extends BaseSkill {
    public NoSkill()

    {
        super(SkillId.NO_SKILL, AnimationType.NONE);

    }

    @Override
    public void activate(BaseCreature source) {

    }

    @Override
    public void affect(Entity target)

    {

    }

    @Override
    public void affect(BaseCreature target)

    {

    }

}
