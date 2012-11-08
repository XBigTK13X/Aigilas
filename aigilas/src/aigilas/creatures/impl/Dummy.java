package aigilas.creatures.impl;

import aigilas.creatures.Stats;
import aigilas.creatures.StatsRegistry;
import aigilas.entities.Elements;
import aigilas.management.Common;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;
import sps.core.Core;

public class Dummy extends BaseEnemy {

    public Dummy() {
        super(ActorTypes.get(Common.Dummy));
        _strategy = StrategyFactory.create(null, this);
        _baseStats = StatsRegistry.get().baseStats(ActorTypes.get(Core.Non_Player));
        _maxStats = new Stats(_baseStats);
        _composition.add(Elements.Physical);
    }
}
