package aigilas.creatures.impl;

import aigilas.Aigilas;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorTypes;

public class BreakingWheel extends BaseEnemy {
    public BreakingWheel() {
        super(ActorTypes.get(Aigilas.Actors.Breaking_Wheel));
        setStrategy(StrategyFactory.create(Strategy.StraightLineRotate, this));
    }
}
