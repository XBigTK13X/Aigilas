package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class GushSkill extends ISkill {
    public GushSkill()

    {
        super(SkillId.GUSH, AnimationType.RANGED);

        add(Elements.WATER);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(10, _source, true);

    }

}
