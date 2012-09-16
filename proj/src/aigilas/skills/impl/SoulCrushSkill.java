package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.core.RNG;

public class SoulCrushSkill extends ISkill {
    public SoulCrushSkill()

    {
        super(SkillId.SOUL_CRUSH, AnimationType.RANGED);

        add(Elements.MENTAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(5, _source, true, StatType.values()[RNG.next(0, 3)]);

    }

}
