package aigilas.creatures.impl;

import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class Hand extends BaseEnemy {
    public Hand() {
        super(ActorTypes.get("Hand"));
        _isBlocking = false;
        _strategy = StrategyFactory.create(Strategy.StraightLine, this);
    }
}
