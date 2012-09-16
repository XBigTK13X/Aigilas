package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class SpeedUpSkill extends ISkill {
    public SpeedUpSkill()

    {
        super(SkillId.SPEED_UP, AnimationType.SELF);

        add(Elements.WATER);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        super.activate(source);
        applyToPlayers(Status.SpeedUp);

    }

}
