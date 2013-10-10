package aigilas.creatures.impl;

import aigilas.Aigilas;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class Serpent extends BaseEnemy {
    public Serpent() {
        super(ActorTypes.get(Aigilas.Actors.Serpent));
        setStrategy(StrategyFactory.create(Strategy.ConfusedAndDying, this));
    }
}
