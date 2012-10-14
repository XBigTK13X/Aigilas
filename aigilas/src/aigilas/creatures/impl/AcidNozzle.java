package aigilas.creatures.impl;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class AcidNozzle extends Minion {
    public AcidNozzle() {
        super(ActorType.Minion, 50f);
        add(SkillId.Acid_Drip);
        _composition.add(Elements.Earth);
        _strategy = StrategyFactory.create(Strategy.MinionRotate, this);
    }
}
