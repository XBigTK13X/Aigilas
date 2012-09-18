package spx.paths;

import spx.core.Point2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Path {
    public final Point2 Finish = new Point2(0, 0);
    private List<Point2> _steps = new ArrayList<>();
    private HashMap<Point2, Point2> _stepLookup = new HashMap<>();
    private float _totalWeight = 0;

    public Path() {
    }

    public Path reset(Point2 start, Point2 finish) {
        _steps.clear();
        _stepLookup.clear();
        _totalWeight = 0;
        moveIndex = -1;
        Finish.copy(finish);
        add(start);
        return this;
    }

    public Path copy(Path source) {
        if (source != null) {
            _stepLookup = StepLookup.copy(source._stepLookup);
            _steps = Walk.copy(source._steps);
            _totalWeight = source._totalWeight;
            Finish.copy(source.Finish);
        }
        moveIndex = -1;
        return this;
    }

    public boolean add(Point2 step) {
        if (!_stepLookup.containsKey(step)) {
            _stepLookup.put(step, step);
            _steps.add(step);
            _totalWeight += step.Weight;
            return true;
        }
        return false;
    }

    public float getCost() {
        return _totalWeight;
    }

    private int moveIndex = -1;

    public boolean hasMoves() {
        return moveIndex < _steps.size();
    }

    public Point2 getNextMove() {
        moveIndex++;
        if (moveIndex >= _steps.size()) {
            return null;
        }
        if (_steps.size() == 0) {
            return null;
        }
        return _steps.size() == 1 ? _steps.get(0) : _steps.get(moveIndex);
    }

    public boolean isDone() {
        return _stepLookup.containsKey(Finish);
    }

    public Point2 getLastStep() {
        return _steps.get(_steps.size() - 1);
    }

    public List<Point2> getNeighbors() {
        return getLastStep().getNeighbors();
    }

    public int length() {
        return _steps.size();
    }
}
