package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;

public class AttackSelfStrategy extends BaseStrategy {
    public AttackSelfStrategy(BaseCreature parent)

    {
        super(parent, Strategy.AttackSelf);

    }

    @Override
    public void act() {
        if (AbleToMove()) {
            _parent.applyDamage(1);
        }
    }
}
