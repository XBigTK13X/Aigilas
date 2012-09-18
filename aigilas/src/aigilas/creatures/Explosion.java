package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class Explosion extends Minion {
    public Explosion() {
        super(ActorType.MINION);
        _strategy = StrategyFactory.create(Strategy.MinionOneUse, this);
        add(SkillId.EXPLODE);
        _composition.add(Elements.FIRE);
    }
}
