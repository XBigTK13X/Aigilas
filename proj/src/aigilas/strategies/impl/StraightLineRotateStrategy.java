package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.RNG;
import spx.entities.CoordVerifier;

public class StraightLineRotateStrategy extends IStrategy {
    private Point2 _direction = new Point2(0, 1);

    public StraightLineRotateStrategy(ICreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.StraightLineRotate);

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    private Point2 target = new Point2(0, 0);

    @Override
    public void act() {
        _parent.moveIfPossible(_direction.PosX, _direction.PosY);
        target.reset(_direction.PosX + _parent.getLocation().PosX, _direction.PosY + _parent.getLocation().PosY);
        if (CoordVerifier.isBlocked(target) && _parent.isCooledDown()) {
            _direction.setX(RNG.Rand.nextInt(3) - 1);
            _direction.setY(RNG.Rand.nextInt(3) - 1);
        }
    }
}
