package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class PoisonCloud extends Minion {
    public PoisonCloud() {
        super(ActorType.MINION);
        _strategy = StrategyFactory.create(Strategy.MinionOneUse, this);
        add(SkillId.POISON_CLOUD);
        _composition.add(Elements.MENTAL);
    }
}
