package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class BreakingWheel extends AbstractCreature {
	public BreakingWheel() {
		super(ActorType.BREAKING_WHEEL);
		_strategy = StrategyFactory.Create(Strategy.StraightLineRotate, this);
		_composition.add(Elements.DARK);
		Strengths(StatType.MOVE_COOL_DOWN, StatType.MOVE_COOL_DOWN);
	}
}
