package aigilas.creatures.impl;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;
import sps.core.Settings;

public class AcidNozzle extends Minion {
    public AcidNozzle() {
        super(ActorType.Minion, Settings.get().defaultSpeed);
        add(SkillId.Acid_Drip);
        _composition.add(Elements.Earth);
        _strategy = StrategyFactory.create(Strategy.MinionRotate, this);
    }
}
