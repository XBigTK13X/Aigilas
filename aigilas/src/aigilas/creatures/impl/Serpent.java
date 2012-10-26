package aigilas.creatures.impl;

import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class Serpent extends BaseEnemy {
    public Serpent() {
        super(ActorType.Serpent);
        _strategy = StrategyFactory.create(Strategy.ConfusedAndDying, this);
    }
}
