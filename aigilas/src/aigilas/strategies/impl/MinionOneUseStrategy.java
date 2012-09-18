package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;

public class MinionOneUseStrategy extends BaseStrategy {
    public MinionOneUseStrategy(BaseCreature parent)

    {
        super(parent, Strategy.MinionOneUse);

        parent.setSkillVector(parent.getSkillVector());
    }

    @Override
    public void act() {
        _parent.useActiveSkill();
        _parent.setInactive();
    }
}
