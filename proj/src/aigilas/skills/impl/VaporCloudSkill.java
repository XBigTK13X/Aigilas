package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class VaporCloudSkill extends ISkill {
    public VaporCloudSkill()

    {
        super(SkillId.VAPOR_CLOUD, AnimationType.CLOUD);

        add(Elements.PHYSICAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void affect(ICreature target)

    {
        target.addBuff(new StatBuff(StatType.MOVE_COOL_DOWN, -10));

    }

}
