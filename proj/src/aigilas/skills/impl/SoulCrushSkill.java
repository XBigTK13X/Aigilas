package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.core.RNG;

public class SoulCrushSkill extends BaseSkill {
    public SoulCrushSkill()

    {
        super(SkillId.SOUL_CRUSH, AnimationType.RANGED);

        add(Elements.MENTAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(5, _source, true, StatType.values()[RNG.next(0, 3)]);

    }

}
