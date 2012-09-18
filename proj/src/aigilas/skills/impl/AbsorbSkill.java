package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AbsorbSkill extends BaseSkill {
    public AbsorbSkill()

    {
        super(SkillId.ABSORB, AnimationType.RANGED);

        add(Elements.LIGHT);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(10, _source);
        _source.applyDamage(-10);
        target.applyDamage(10, _source, true, StatType.MANA);
        _source.applyDamage(-10, _source, true, StatType.MANA);

    }

}
