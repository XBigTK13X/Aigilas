package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class BreakingWheel extends BaseEnemy {
    public BreakingWheel() {
        super(ActorType.Breaking_Wheel);
        _strategy = StrategyFactory.create(Strategy.StraightLineRotate, this);
        _composition.add(Elements.Dark);
        strengths(StatType.Move_Cool_Down, StatType.Move_Cool_Down);
    }
}
