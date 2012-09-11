package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class Explosion extends Minion {
	public Explosion() {
		super(ActorType.MINION);
		_strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
		Add(SkillId.EXPLODE);
		_composition.add(Elements.FIRE);
	}
}
