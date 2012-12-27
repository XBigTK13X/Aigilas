package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.core.RNG;

public class ConfusedStrategy extends BaseStrategy {
    public ConfusedStrategy(BaseCreature parent) {
        super(parent, Strategy.Confused);
    }

    @Override
    public void act() {
        _parent.moveIfPossible(RNG.coinFlip() ? 1 : RNG.coinFlip() ? 0 : -1, RNG.coinFlip() ? 1 : RNG.coinFlip() ? 0 : -1);
    }
}
