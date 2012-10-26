package aigilas.statuses.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.statuses.BaseStatus;
import aigilas.statuses.Status;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class BoilStatus extends BaseStatus {
    private Strategy previousStrategy;
    private float _previousHealth = 0;
    private boolean _countDownFailed = false;
    private static final int _countdownMax = 10;
    private int _countdown = _countdownMax;

    public BoilStatus(BaseCreature target) {
        super(target, Status.Boil);
    }

    @Override
    public void setup() {
        super.setup();
        previousStrategy = _target.getStrategyId();
        _target.setStrategy(StrategyFactory.create(null, _target));
        _target.getTargets().addTargetTypes(ActorType.Player);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        if (!_countDownFailed) {
            _target.getTargets().findClosest().applyDamage(impl.Info.Magnitude);
        }
        _target.setStrategy(StrategyFactory.create(previousStrategy, _target));
    }

    @Override
    public void update() {
        super.update();
        if (_target.isCooledDown()) {
            _countdown--;
            _target.write(sps.util.StringStorage.get(_strength));
            if (_countdown <= 0) {
                _countdown = _countdownMax;
            }
        }
        if (_target.get(StatType.Health) < _previousHealth) {
            _countDownFailed = true;
            cleanup();
        }
        _previousHealth = _target.get(StatType.Health);
    }
}
