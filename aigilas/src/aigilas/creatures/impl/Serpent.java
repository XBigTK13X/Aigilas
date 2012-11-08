package aigilas.creatures.impl;

import aigilas.Common;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class Serpent extends BaseEnemy {
    public Serpent() {
        super(ActorTypes.get(Common.Serpent));
        _strategy = StrategyFactory.create(Strategy.ConfusedAndDying, this);
    }
}
