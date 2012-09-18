package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class FireballSkill extends BaseSkill {
    public FireballSkill()

    {
        super(SkillId.FIREBALL, AnimationType.RANGED);

        add(Elements.FIRE);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(20, _source);

    }

}
