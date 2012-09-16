package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class ColdShoulderSkill extends ISkill {
    public ColdShoulderSkill()

    {
        super(SkillId.COLD_SHOULDER, AnimationType.SELF);

        add(Elements.FIRE);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        super.activate(source);
        applyToPlayers(Status.ColdShoulder);

    }

}
