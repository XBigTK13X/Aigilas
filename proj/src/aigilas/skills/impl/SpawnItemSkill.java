package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.items.ItemFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class SpawnItemSkill extends ISkill {
    public SpawnItemSkill()

    {
        super(SkillId.SPAWN_ITEM, AnimationType.SELF);

        add(Elements.EARTH);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(ICreature source) {
        super.activate(source);
        ItemFactory.createRandomPlain(source.getLocation());

    }

}
