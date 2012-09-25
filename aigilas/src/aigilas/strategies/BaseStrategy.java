package aigilas.strategies;

import aigilas.creatures.BaseCreature;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.core.RNG;
import sps.entities.HitTest;
import sps.paths.Path;
import sps.paths.PathFinder;

import java.util.List;

public abstract class BaseStrategy {
    protected final TargetSet _targets;
    protected final BaseCreature _parent;

    protected BaseCreature opponent;
    protected final Path targetPath = new Path();
    protected final Point2 nextMove = new Point2(0, 0);

    protected final Strategy _strategyId;

    protected BaseStrategy(BaseCreature parent, Strategy strategyId) {
        _targets = new TargetSet(parent);
        _parent = parent;
        _strategyId = strategyId;
    }

    public abstract void act();

    public TargetSet getTargets() {
        return _targets;
    }

    public void addTargets(BaseCreature source)

    {
        _targets.addTargets(source);
    }

    protected boolean AbleToMove() {
        opponent = _targets.findClosest();
        // Every player is dead
        if (targetPath.getLastStep() == null || (null != opponent && !targetPath.getLastStep().isSameSpot(opponent.getLocation()))){
            targetPath.copy(PathFinder.findNextMove(_parent.getLocation(), opponent.getLocation()));
        }
        if (targetPath.hasMoves() && _parent.isCooledDown()) {
            return true;
        }
        return false;
    }

    protected final Point2 diff = new Point2(0, 0);

    protected Point2 CalculateTargetVector(Point2 source, Point2 dest) {
        diff.setX(source.GridX - dest.GridX);
        diff.setY(source.GridY - dest.GridY);
        if (diff.GridY == 0) {
            if (diff.GridX > 0) {
                diff.setX(-1f);
            }
            else {
                diff.setX(1f);
            }
        }
        else if (diff.GridX == 0) {
            if (diff.GridY > 0) {
                diff.setY(-1f);
            }
            else {
                diff.setY(1f);
            }
        }
        else {
            diff.setX(0);
            diff.setY(0);
        }
        return diff;
    }

    public Strategy getId() {
        return _strategyId;
    }

    public List<ActorType> getTargetActorTypes()

    {
        return _targets.getTargetActorTypes();
    }

    public Path getPath() {
        return targetPath;
    }
}
