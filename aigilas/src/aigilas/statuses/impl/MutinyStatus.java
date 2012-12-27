package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureAction;
import aigilas.statuses.BaseStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class MutinyStatus extends BaseStatus {
    public MutinyStatus(BaseCreature target) {
        super(target);
        _prevents.add(CreatureAction.WontHitNonTargets);
    }

    @Override
    public void setup() {
        super.setup();
        causedStrategy = StrategyFactory.create(Strategy.Mutiny, _target);
        _target.setStrategy(causedStrategy);
    }
}
