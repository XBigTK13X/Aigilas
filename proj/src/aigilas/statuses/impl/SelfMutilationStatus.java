package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class SelfMutilationStatus extends IStatus {
    private Strategy previousStrategy;

    public SelfMutilationStatus(ICreature target)

    {
        super(target);

    }

    @Override
    public void setup() {
        super.setup();
        previousStrategy = _target.getStrategyId();
        _target.setStrategy(StrategyFactory.create(Strategy.AttackSelf, _target));
    }

    @Override
    public void cleanup() {
        super.cleanup();
        _target.setStrategy(StrategyFactory.create(previousStrategy, _target));
    }
}
