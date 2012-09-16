package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class FireballSkill extends ISkill {
    public FireballSkill()

    {
        super(SkillId.FIREBALL, AnimationType.RANGED);

        add(Elements.FIRE);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(20, _source);

    }

}
