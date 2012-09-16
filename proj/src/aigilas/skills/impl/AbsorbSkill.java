package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class AbsorbSkill extends ISkill {
    public AbsorbSkill()

    {
        super(SkillId.ABSORB, AnimationType.RANGED);

        add(Elements.LIGHT);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.applyDamage(10, _source);
        _source.applyDamage(-10);
        target.applyDamage(10, _source, true, StatType.MANA);
        _source.applyDamage(-10, _source, true, StatType.MANA);

    }

}
