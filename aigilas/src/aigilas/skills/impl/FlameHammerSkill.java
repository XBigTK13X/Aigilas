package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class FlameHammerSkill extends BaseSkill {
    public FlameHammerSkill()

    {
        super(SkillId.FLAME_HAMMER, AnimationType.ROTATE);


    }

    @Override
    public void affect(BaseCreature target) {
        target.applyDamage(3f, _source);
    }

}
