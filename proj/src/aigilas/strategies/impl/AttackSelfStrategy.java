package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class AttackSelfStrategy extends IStrategy {
    public AttackSelfStrategy(ICreature parent)

    {
        super(parent, Strategy.AttackSelf);

    }

    @Override
    public void Act() {
        if (AbleToMove()) {
            _parent.ApplyDamage(1);
        }
    }
}
