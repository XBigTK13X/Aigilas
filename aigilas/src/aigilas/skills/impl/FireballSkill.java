package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class FireballSkill extends BaseSkill {
    public FireballSkill()

    {
        super(SkillId.Fireball, AnimationType.RANGED);


    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(20, _source);

    }

}
