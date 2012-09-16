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

        Add(Elements.PHYSICAL);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {
        target.AddBuff(new StatBuff(StatType.MOVE_COOL_DOWN, -10));

    }

}
