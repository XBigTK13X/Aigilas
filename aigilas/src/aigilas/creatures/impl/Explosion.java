package aigilas.creatures.impl;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class Explosion extends Minion {
    public Explosion() {
        super(ActorType.Minion);
        _strategy = StrategyFactory.create(Strategy.MinionOneUse, this);
        add(SkillId.Explode);
        _composition.add(Elements.Fire);
    }
}
