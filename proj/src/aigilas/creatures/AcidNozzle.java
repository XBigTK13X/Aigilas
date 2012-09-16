package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import spx.bridge.ActorType;

public class AcidNozzle extends Minion {
    public AcidNozzle() {
        super(ActorType.MINION, 50f);
        add(SkillId.ACID_DRIP);
        _composition.add(Elements.EARTH);
        _strategy = StrategyFactory.create(Strategy.MinionRotate, this);
    }
}
