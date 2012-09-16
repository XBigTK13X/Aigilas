package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;
import spx.core.Settings;

public class StraightLineStrategy extends IStrategy {
    public StraightLineStrategy(ICreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.StraightLine);

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    @Override
    public void act() {
        _parent.moveIfPossible(0, 1);
        if (_parent.getLocation().GridY >= Settings.get().tileMapHeight - 1) {
            _parent.setInactive();
        }
    }
}
