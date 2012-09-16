package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class ElectrifySkill extends ISkill {
    public ElectrifySkill()

    {
        super(SkillId.ELECTRIFY, AnimationType.SELF);

        add(Elements.AIR);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        super.activate(source);
        StatusFactory.apply(source, Status.Electrify);

    }

}
