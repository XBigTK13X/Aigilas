package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.BaseStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class SelfMutilationStatus extends BaseStatus {
    public SelfMutilationStatus(BaseCreature target) {
        super(target);
    }

    @Override
    public void setup() {
        super.setup();
        causedStrategy = StrategyFactory.create(Strategy.AttackSelf, _target);
        _target.setStrategy(causedStrategy);
    }
}
