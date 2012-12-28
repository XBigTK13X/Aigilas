package aigilas.creatures.impl;

import aigilas.Common;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class Hand extends BaseEnemy {
    public Hand() {
        super(ActorTypes.get(Common.Actors.Hand));
        _isBlocking = false;
        setStrategy(StrategyFactory.create(Strategy.StraightLine, this));
    }
}
