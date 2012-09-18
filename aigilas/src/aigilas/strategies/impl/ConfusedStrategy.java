package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.core.RNG;

public class ConfusedStrategy extends BaseStrategy {
    public ConfusedStrategy(BaseCreature parent)

    {
        super(parent, Strategy.Confused);

    }

    @Override
    public void act() {
        _parent.moveIfPossible(RNG.Rand.nextInt(3) - 1, RNG.Rand.nextInt(3) - 1);
    }
}
