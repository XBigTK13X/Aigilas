package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class MutinyStatus extends IStatus {
    private Strategy previousStrategy;

    public MutinyStatus(ICreature target) {
        super(target);

        _prevents.add(CreatureAction.WontHitNonTargets);
    }

    @Override
    public void setup() {
        super.setup();
        previousStrategy = _target.getStrategyId();
        _target.setStrategy(StrategyFactory.create(Strategy.Mutiny, _target));
    }

    @Override
    public void cleanup() {
        super.cleanup();
        _target.setStrategy(StrategyFactory.create(previousStrategy, _target));
    }
}
