package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AbsorbSkill extends BaseSkill {
    public AbsorbSkill()
    {
        super(SkillId.ABSORB, AnimationType.RANGED);
    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(_id.Info.Magnitude, _source);
        _source.applyDamage(-_id.Info.Magnitude);
        target.applyDamage(_id.Info.Magnitude, _source, true, StatType.MANA);
        _source.applyDamage(-_id.Info.Magnitude, _source, true, StatType.MANA);

    }

}
