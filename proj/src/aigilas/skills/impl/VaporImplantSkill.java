package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.entities.IEntity;

public class VaporImplantSkill extends ISkill {
    public VaporImplantSkill()

    {
        super(SkillId.VAPOR_IMPLANT, AnimationType.RANGED);

        addCost(StatType.MANA, 10);
        add(Elements.PHYSICAL, Elements.AIR);

    }

    @Override
    public void affect(IEntity target)

    {
        CreatureFactory.createMinion(SkillId.VAPOR_CLOUD, _source, null, target.getLocation());

    }

}
