package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import spx.bridge.ActorType;

public class PoisonCloud extends Minion {
    public PoisonCloud() {
        super(ActorType.MINION);
        _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
        Add(SkillId.POISON_CLOUD);
        _composition.add(Elements.MENTAL);
    }
}
