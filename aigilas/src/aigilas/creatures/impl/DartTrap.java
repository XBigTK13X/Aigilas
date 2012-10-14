package aigilas.creatures.impl;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class DartTrap extends Minion {
    public DartTrap() {
        super(ActorType.Minion);
        _strategy = StrategyFactory.create(Strategy.MinionFire, this);
        add(SkillId.Dart);
        _composition.add(Elements.Dark);
    }
}
