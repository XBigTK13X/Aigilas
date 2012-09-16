package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.entities.IEntity;

public class NoSkill extends ISkill {
    public NoSkill()

    {
        super(SkillId.NO_SKILL, AnimationType.NONE);

    }

    @Override
    public void Activate(ICreature source) {

    }

    @Override
    public void Affect(IEntity target)

    {

    }

    @Override
    public void Affect(ICreature target)

    {

    }

}
