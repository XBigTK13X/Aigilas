package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.core.RNG;
import sps.entities.CoordVerifier;

public class StraightLineRotateStrategy extends BaseStrategy {
    private final Point2 _direction = new Point2(0, 1);

    public StraightLineRotateStrategy(BaseCreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.StraightLineRotate);

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    private final Point2 target = new Point2(0, 0);

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
