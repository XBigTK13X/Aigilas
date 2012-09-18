package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class FlameHammerSkill extends BaseSkill {
    public FlameHammerSkill()

    {
        super(SkillId.FLAME_HAMMER, AnimationType.ROTATE);

        add(Elements.FIRE);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)
    {
        target.applyDamage(3f, _source);
    }

}
