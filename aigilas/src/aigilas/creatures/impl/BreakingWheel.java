package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;

public class BreakingWheel extends BaseEnemy {
    public BreakingWheel() {
        super(ActorType.BREAKING_WHEEL);
        _strategy = StrategyFactory.create(Strategy.StraightLineRotate, this);
        _composition.add(Elements.DARK);
        Strengths(StatType.MOVE_COOL_DOWN, StatType.MOVE_COOL_DOWN);
    }
}
