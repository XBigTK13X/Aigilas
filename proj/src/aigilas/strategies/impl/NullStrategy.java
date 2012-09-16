package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import spx.bridge.ActorType;

public class NullStrategy extends IStrategy {

    public NullStrategy(ICreature parent, ActorType... targetTypes)

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
