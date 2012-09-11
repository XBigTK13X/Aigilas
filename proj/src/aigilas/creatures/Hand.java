package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class Hand extends AbstractCreature {
	public Hand() {
		super(ActorType.HAND, SpriteType.HAND);
		Compose(Elements.PHYSICAL);
		Strengths(StatType.STRENGTH, StatType.STRENGTH);
		_isBlocking = false;
		_strategy = StrategyFactory.Create(Strategy.StraightLine, this);
	}
}
