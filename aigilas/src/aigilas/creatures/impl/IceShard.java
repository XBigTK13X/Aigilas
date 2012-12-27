package aigilas.creatures.impl;

import aigilas.Common;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class IceShard extends Minion {
    public IceShard() {
        super(ActorTypes.get(Common.Minion));
        setStrategy(StrategyFactory.create(Strategy.MinionOneUse, this));
        add(SkillId.Ice_Shard);
        _composition.add(Elements.Water);
    }
}
