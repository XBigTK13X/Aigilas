package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class IceShard extends Minion {
	public IceShard() {
		super(ActorType.MINION);
		_strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
		Add(SkillId.ICE_SHARD);
		_composition.add(Elements.WATER);
	}
}
