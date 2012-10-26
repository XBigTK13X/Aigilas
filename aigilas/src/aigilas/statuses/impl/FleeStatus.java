package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureAction;
import aigilas.statuses.BaseStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class FleeStatus extends BaseStatus {
    private Strategy previousStrategy;

    public FleeStatus(BaseCreature target) {
        super(target);
        _prevents.add(CreatureAction.WontHitNonTargets);
    }

    @Override
    public void setup() {
        super.setup();
        previousStrategy = _target.getStrategyId();
        _target.setStrategy(StrategyFactory.create(Strategy.Flee, _target));
    }

    @Override
    public void cleanup() {
        super.cleanup();
        _target.setStrategy(StrategyFactory.create(previousStrategy, _target));
    }
}
