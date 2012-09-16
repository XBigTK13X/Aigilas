package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class RegenAllSkill extends ISkill {
    public RegenAllSkill()

    {
        super(SkillId.REGEN_ALL, AnimationType.SELF);

        add(Elements.LIGHT);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        super.activate(source);
        applyToPlayers(Status.Regen);

    }

}
