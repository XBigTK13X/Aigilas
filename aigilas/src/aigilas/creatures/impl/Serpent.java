package aigilas.creatures.impl;

import aigilas.management.SpriteType;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class Serpent extends BaseEnemy {
    public Serpent() {
        super(ActorType.Serpent, SpriteType.Sloth);
        _strategy = StrategyFactory.create(Strategy.ConfusedAndDying, this);
    }
}
