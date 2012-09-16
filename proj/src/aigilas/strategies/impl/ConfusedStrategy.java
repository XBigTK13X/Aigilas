package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.core.RNG;

public class ConfusedStrategy extends IStrategy {
    public ConfusedStrategy(ICreature parent)

    {
        super(parent, Strategy.Confused);

    }

    @Override
    public void act() {
        _parent.moveIfPossible(RNG.Rand.nextInt(3) - 1, RNG.Rand.nextInt(3) - 1);
    }
}
