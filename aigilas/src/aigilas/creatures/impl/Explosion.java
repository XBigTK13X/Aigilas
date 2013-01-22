package aigilas.creatures.impl;

import aigilas.Aigilas;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class Explosion extends Minion {
    public Explosion() {
        super(ActorTypes.get(Aigilas.Actors.Minion));
        setStrategy(StrategyFactory.create(Strategy.MinionOneUse, this));
        add(SkillId.Explode);
        _composition.add(Elements.Fire);
    }
}
