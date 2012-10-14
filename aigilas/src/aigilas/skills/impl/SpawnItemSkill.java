package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.items.ItemFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

public class SpawnItemSkill extends BaseSkill {
    public SpawnItemSkill() {
        super(SkillId.Spawn_Item, AnimationType.SELF);
    }

    @Override
    public void activate(BaseCreature source) {
        super.activate(source);
        ItemFactory.createRandomPlain(source.getLocation());
    }
}
