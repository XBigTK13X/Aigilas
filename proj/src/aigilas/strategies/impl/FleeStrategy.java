package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;
import spx.core.Point2;

public class FleeStrategy extends IStrategy {
    public FleeStrategy(ICreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.Flee);

        for (ActorType targetType : targetTypes) {
            _targets.AddTargetTypes(targetType);
        }
    }

    private Point2 _transfer = new Point2(0, 0);

    @Override
    public void Act() {
        if (AbleToMove()) {
            _transfer = targetPath.GetNextMove();
            if (_transfer != null) {
                nextMove.Copy(_parent.GetLocation().Add(_transfer.Minus(_parent.GetLocation()).Rotate180()));
                _parent.MoveTo(nextMove);
            }
        }
    }
}
