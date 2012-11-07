package aigilas.creatures.impl;

import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class BreakingWheel extends BaseEnemy {
    public BreakingWheel() {
        super(ActorTypes.get("Breaking_Wheel"));
        _strategy = StrategyFactory.create(Strategy.StraightLineRotate, this);
    }
}
