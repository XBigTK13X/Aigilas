package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class ConfusionStatus extends IStatus {
	private Strategy previousStrategy;

	public ConfusionStatus(ICreature target)

	{
		super(target);

		_prevents.add(CreatureAction.WontHitNonTargets);
	}

	@Override
	public void Setup() {
		super.Setup();
		previousStrategy = _target.GetStrategyId();
		_target.SetStrategy(StrategyFactory.Create(Strategy.Confused, _target));
	}

	@Override
	public void Cleanup() {
		super.Cleanup();
		_target.SetStrategy(StrategyFactory.Create(previousStrategy, _target));
	}
}
