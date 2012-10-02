package aigilas.creatures.impl;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class IceShard extends Minion {
    public IceShard() {
        super(ActorType.MINION);
        _strategy = StrategyFactory.create(Strategy.MinionOneUse, this);
        add(SkillId.ICE_SHARD);
        _composition.add(Elements.WATER);
    }
}
