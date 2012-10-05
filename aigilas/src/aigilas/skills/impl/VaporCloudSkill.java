package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class VaporCloudSkill extends BaseSkill {
    public VaporCloudSkill()

    {
        super(SkillId.VAPOR_CLOUD, AnimationType.CLOUD);

        add(Elements.PHYSICAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(BaseCreature target)

    {
        target.butBuff(new StatBuff(StatType.MOVE_COOL_DOWN, -10));

    }

}
