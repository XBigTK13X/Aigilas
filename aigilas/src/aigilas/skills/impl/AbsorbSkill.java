package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class AbsorbSkill extends BaseSkill {
    public AbsorbSkill() {
        super(SkillId.Absorb, AnimationType.RANGED);
    }

    @Override
    public void affect(BaseCreature target) {
        target.applyDamage(_id.Info.Magnitude, _source);
        _source.applyDamage(-_id.Info.Magnitude);
        target.applyDamage(_id.Info.Magnitude, _source, true, StatType.Energy);
        _source.applyDamage(-_id.Info.Magnitude, _source, true, StatType.Energy);
    }
}
