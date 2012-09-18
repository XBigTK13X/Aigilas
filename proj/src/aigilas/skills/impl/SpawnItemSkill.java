package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.items.ItemFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class SpawnItemSkill extends BaseSkill {
    public SpawnItemSkill()

    {
        super(SkillId.SPAWN_ITEM, AnimationType.SELF);

        add(Elements.EARTH);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        ItemFactory.createRandomPlain(source.getLocation());

    }

}
