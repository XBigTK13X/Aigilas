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

        Add(Elements.WATER);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Activate(ICreature source) {
        super.Activate(source);
        ApplyToPlayers(Status.SpeedUp);

    }

}
