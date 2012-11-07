package aigilas.creatures.impl;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class AcidNozzle extends Minion {
    public AcidNozzle() {
        super(ActorTypes.get("Minion"));
        add(SkillId.Acid_Drip);
        _composition.add(Elements.Earth);
        _strategy = StrategyFactory.create(Strategy.MinionRotate, this);
    }
}
