package aigilas.creatures.impl;

import aigilas.management.SpriteType;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class Hand extends BaseEnemy {
    public Hand() {
        super(ActorType.Hand, SpriteType.Hand);
        _isBlocking = false;
        _strategy = StrategyFactory.create(Strategy.StraightLine, this);
    }
}
