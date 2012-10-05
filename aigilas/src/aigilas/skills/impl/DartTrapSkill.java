package aigilas.skills.impl;

import aigilas.creatures.impl.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.entities.SkillEffect;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.entities.Entity;

public class DartTrapSkill extends BaseSkill {
    public DartTrapSkill()

    {
        super(SkillId.DART_TRAP, AnimationType.RANGED);

        addCost(StatType.MANA, 10);
        add(Elements.DARK);

    }

    @Override
    public void cleanup(Entity target, SkillEffect source)

    {
        CreatureFactory.createMinion(_id, _source, source, target.getLocation());

    }

}
