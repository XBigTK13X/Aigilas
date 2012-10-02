package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class Serpent extends BaseEnemy {
    public Serpent() {
        super(ActorType.SERPENT, SpriteType.SLOTH);
        Compose(Elements.EARTH);
        Strengths(StatType.HEALTH, StatType.HEALTH, StatType.HEALTH);
        _strategy = StrategyFactory.create(Strategy.ConfusedAndDying, this);
    }
}
