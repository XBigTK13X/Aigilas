package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class MinionCloudStrategy extends IStrategy {
    public MinionCloudStrategy(ICreature parent)

    {
        super(parent, Strategy.MinionCloud);

        parent.setSkillVector(parent.getSkillVector());
    }

    @Override
    public void act() {
        _parent.useActiveSkill();
        _parent.setInactive();
    }
}
