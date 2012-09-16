package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class FlameHammerSkill extends ISkill {
    public FlameHammerSkill()

    {
        super(SkillId.FLAME_HAMMER, AnimationType.ROTATE);

        add(Elements.FIRE);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(3f, _source);

    }

}
