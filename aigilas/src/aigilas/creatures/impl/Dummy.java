package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.creatures.Stats;
import aigilas.creatures.StatsRegistry;
import aigilas.entities.Elements;
import aigilas.strategies.StrategyFactory;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.ActorType;

public class Dummy extends BaseEnemy {

    public Dummy() {
        super(ActorType.Dummy);
        _graphic.setColor(Color.ORANGE);
        _strategy = StrategyFactory.create(null, this);
        _baseStats = StatsRegistry.get().baseStats(ActorType.Non_Player);
        _baseStats.set(StatType.Regen, 100000);
        _maxStats = new Stats(_baseStats);
        _composition.add(Elements.Physical);
    }
}
