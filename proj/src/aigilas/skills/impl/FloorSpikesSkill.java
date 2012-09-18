package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class FloorSpikesSkill extends BaseSkill {
    public FloorSpikesSkill()

    {
        super(SkillId.FLOOR_SPIKES, AnimationType.STATIONARY, Float.MAX_VALUE, true);

        addCost(StatType.MANA, 20);
        add(Elements.EARTH);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.applyDamage(80, _source);

    }

}
