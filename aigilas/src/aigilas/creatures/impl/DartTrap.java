package aigilas.creatures.impl;

import aigilas.Common;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class DartTrap extends Minion {
    public DartTrap() {
        super(ActorTypes.get(Common.Minion));
        _strategy = StrategyFactory.create(Strategy.MinionFire, this);
        add(SkillId.Dart);
        _composition.add(Elements.Dark);
    }
}
