package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class MinionOneUseStrategy extends IStrategy {
    public MinionOneUseStrategy(ICreature parent)

    {
        super(parent, Strategy.MinionOneUse);

        parent.SetSkillVector(parent.GetSkillVector());
    }

    @Override
    public void Act() {
        _parent.UseActiveSkill();
        _parent.SetInactive();
    }
}
