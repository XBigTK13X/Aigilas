package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ExplodeSkill extends ISkill {
    public ExplodeSkill()

    {
        super(SkillId.EXPLODE, AnimationType.CLOUD);

        add(Elements.FIRE);
        addCost(StatType.MANA, 0);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(10, _source, true);

    }

}
