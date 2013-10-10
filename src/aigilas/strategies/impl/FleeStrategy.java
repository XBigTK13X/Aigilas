package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorType;
import sps.core.Point2;

public class FleeStrategy extends BaseStrategy {
    public FleeStrategy(BaseCreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.Flee);

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    private Point2 _transfer = new Point2(0, 0);

    @Override
    public void act() {
        if (AbleToMove()) {
            _transfer = targetPath.getNextMove();
            if (_transfer != null) {
                nextMove.copy(_parent.getLocation().add(_transfer.minus(_parent.getLocation()).rotate(180)));
                _parent.moveTo(nextMove);
            }
        }
    }
}
