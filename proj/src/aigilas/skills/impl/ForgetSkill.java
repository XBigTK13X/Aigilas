package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ForgetSkill extends ISkill

{
    public ForgetSkill()

    {
        super(SkillId.FORGET_SKILL, AnimationType.SELF);

        add(Elements.MENTAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        super.activate(source);
        _source.removeLeastUsedSkill();

    }

}
