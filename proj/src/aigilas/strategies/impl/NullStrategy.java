package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import spx.bridge.ActorType;

public class NullStrategy extends BaseStrategy {

    public NullStrategy(BaseCreature parent, ActorType... targetTypes)

    {
        super(parent, null);

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    @Override
    public void act() {

    }
}
