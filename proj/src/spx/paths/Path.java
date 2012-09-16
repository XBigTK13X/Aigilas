package spx.paths;

import spx.core.Point2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Path {
    public Point2 Finish = new Point2(0, 0);
    private List<Point2> _steps = new ArrayList<Point2>();
    private HashMap<Point2, Point2> _stepLookup = new HashMap<Point2, Point2>();
    private float _totalWeight = 0;

    public Path() {
    }

    public Path Reset(Point2 start, Point2 finish) {
        _steps.clear();
        _stepLookup.clear();
        _totalWeight = 0;
        moveIndex = -1;
        Finish.Copy(finish);
        Add(start);
        return this;
    }

    public Path Copy(Path source) {
        if (source != null) {
            _stepLookup = StepLookup.Copy(source._stepLookup);
            _steps = Walk.Copy(source._steps);
            _totalWeight = source._totalWeight;
            Finish.Copy(source.Finish);
        }
        moveIndex = -1;
        return this;
    }

    public boolean Add(Point2 step) {
        if (!_stepLookup.containsKey(step)) {
            _stepLookup.put(step, step);
            _steps.add(step);
            _totalWeight += step.Weight;
            return true;
        }
        return false;
    }

    public float GetCost() {
        return _totalWeight;
    }

    private int moveIndex = -1;

    public boolean HasMoves() {
        return moveIndex < _steps.size();
    }

    public Point2 GetNextMove() {
        moveIndex++;
        if (moveIndex >= _steps.size()) {
            return null;
        }
        if (_steps.size() == 0) {
            return null;
        }
        return _steps.size() == 1 ? _steps.get(0) : _steps.get(moveIndex);
    }

    public boolean IsDone() {
        return _stepLookup.containsKey(Finish);
    }

    public Point2 GetLastStep() {
        return _steps.get(_steps.size() - 1);
    }

    public List<Point2> GetNeighbors() {
        return GetLastStep().GetNeighbors();
    }

    public int Length() {
        return _steps.size();
    }
}
