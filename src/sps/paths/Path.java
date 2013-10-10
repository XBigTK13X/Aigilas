package sps.paths;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.bridge.DrawDepths;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.core.SpsConfig;
import sps.graphics.Assets;
import sps.graphics.Renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {
    private List<Point2> _steps = new ArrayList<Point2>();
    private int moveIndex = -1;

    public Path() {
    }

    public Path(Node node) {
        _steps.add(node.Point);
        while (node.Parent != null) {
            _steps.add(node.Parent.Point);
            node = node.Parent;
        }
        Collections.reverse(_steps);
    }

    public boolean hasMoves() {
        return moveIndex < _steps.size();
    }

    public Point2 getNextMove() {
        if (_steps.size() == 0 || moveIndex >= _steps.size() - 1) {
            return null;
        }
        return _steps.size() == 1 ? _steps.get(0) : _steps.get(++moveIndex);
    }

    public Point2 getLastStep() {
        if (_steps.size() == 0) {
            return null;
        }
        return _steps.get(_steps.size() - 1);
    }

    private static Sprite _t;

    public void draw() {
        if (SpsConfig.get().viewPaths) {
            if (_t == null) {
                _t = Assets.get().sprite(0);
            }
            for (Point2 step : _steps) {
                Renderer.get().draw(_t, new Point2(step.PosX, step.PosY), DrawDepths.get(Sps.DrawDepths.Debug), Color.ORANGE);
            }
        }
    }
}
