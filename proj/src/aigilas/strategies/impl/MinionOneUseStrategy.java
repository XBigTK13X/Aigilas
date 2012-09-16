package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class MinionOneUseStrategy extends IStrategy {
    public MinionOneUseStrategy(ICreature parent)

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
