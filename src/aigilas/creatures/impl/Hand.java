package aigilas.creatures.impl;

import aigilas.Aigilas;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class Hand extends BaseEnemy {
    public Hand() {
        super(ActorTypes.get(Aigilas.Actors.Hand));
        _isBlocking = false;
        setStrategy(StrategyFactory.create(Strategy.StraightLine, this));
    }
}
