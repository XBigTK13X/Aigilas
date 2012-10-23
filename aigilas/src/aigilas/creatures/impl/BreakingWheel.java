package aigilas.creatures.impl;

import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class BreakingWheel extends BaseEnemy {
    public BreakingWheel() {
        super(ActorType.Breaking_Wheel);
        _strategy = StrategyFactory.create(Strategy.StraightLineRotate, this);
    }
}
