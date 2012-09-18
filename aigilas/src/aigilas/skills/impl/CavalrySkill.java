package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;

public class CavalrySkill extends BaseSkill {
    public CavalrySkill()

    {
        super(SkillId.CAVALRY, AnimationType.SELF);

        add(Elements.DARK, Elements.EARTH);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        applyToPlayers(Status.DefenseUp);
    }
}
