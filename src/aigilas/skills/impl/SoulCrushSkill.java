package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.core.RNG;

public class SoulCrushSkill extends BaseSkill {
    public SoulCrushSkill()

    {
        super(SkillId.Soul_Crush, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(5, _source, true, StatType.values()[RNG.next(0, 3)]);

    }

}
