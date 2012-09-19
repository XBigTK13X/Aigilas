package sps.core;

import sps.devtools.DevConsole;

import java.util.ArrayList;
import java.util.List;

public class Point2 {
    public static final Point2 Zero = new Point2(0, 0);
    private static final float halfHeight = Settings.get().spriteHeight / 2;
    private static final float halfWidth = Settings.get().spriteWidth / 2;

    public float X;
    public float Y;
    public float Weight;
    public int GridX;
    public int GridY;
    public float PosX;
    public float PosY;
    public float PosCenterX;
    public float PosCenterY;

    public static final Point2[] _rotateTargets = {new Point2(1, 0), new Point2(1, 1), new Point2(0, 1), new Point2(0, -1), new Point2(-1, -1), new Point2(-1, 0), new Point2(-1, 1), new Point2(1, -1)};

    public static final Point2[][] _locations = new Point2[Settings.get().tileMapHeight][Settings.get().tileMapWidth];

    public Point2() {
    }

    public Point2(float x, float y, int weight) {
        initThis(x, y, weight);
    }

    public Point2(Point2 target) {
        initThis(target.X, target.Y, 0);
    }

    public Point2(int x, int y) {
        initThis(x, y, 0);
    }

    public Point2(float x, float y) {
        initThis(x, y, 0);
    }

    private void initThis(float x, float y, int weight) {
        setX(x);
        setY(y);
        Weight = weight;
    }

    public void reset(float x, float y) {
        setX(x);
        setY(y);
    }

    public void copy(Point2 point) {
        if (point != null) {
            setX(point.X);
            setY(point.Y);
        }
    }

    public Point2 add(Point2 target) {
        return new Point2(GridX + target.GridX, GridY + target.GridY);
    }

    public Point2 add(int dX, int dY) {
        return new Point2(X + dX, Y + dY);
    }

    public Point2 minus(Point2 target) {
        return new Point2(GridX - target.GridX, GridY - target.GridY);
    }

    @Override
    public int hashCode() {
        return GridX + 1000 * GridY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Point2.class) {
            Point2 target = (Point2) obj;
            return target.GridX == GridX && target.GridY == GridY;
        }
        return false;
    }

    public void setX(float xValue) {
        X = xValue;
        boolean isGrid = (Math.abs(X) < Settings.get().tileMapWidth);
        PosX = (isGrid) ? X * Settings.get().spriteWidth : X;
        PosCenterX = PosX + halfWidth;
        GridX = (isGrid) ? (int) X : (int) (X / Settings.get().spriteWidth);
    }

    public void setY(float yValue) {
        Y = yValue;
        boolean isGrid = (Math.abs(Y) < Settings.get().tileMapWidth);
        PosY = (isGrid) ? Y * Settings.get().spriteHeight : Y;
        PosCenterY = PosY + halfHeight;
        GridY = (isGrid) ? (int) Y : (int) (Y / Settings.get().spriteHeight);
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public boolean isZero() {
        return X == 0 && Y == 0;
    }

    public static float calculateDistanceSquared(Point2 source, Point2 target) {
        return (float) (Math.pow(source.PosY - target.PosY, 2) + Math.pow(source.PosX - target.PosX, 2));
    }

    private static final List<Point2> _neighbors = new ArrayList<>();

    public List<Point2> getNeighbors() {
        if (_locations[0][0] == null) {
            for (int ii = 0; ii < Settings.get().tileMapHeight; ii++) {
                for (int jj = 0; jj < Settings.get().tileMapWidth; jj++) {
                    _locations[ii][jj] = new Point2(jj, ii);
                }
            }
        }
        _neighbors.clear();
        for (int ii = -1; ii < 2; ii++) {
            for (int jj = -1; jj < 2; jj++) {
                if (ii != 0 || jj != 0) {
                    _neighbors.add(_locations[GridY + ii][GridX + jj]);
                }
            }
        }
        return _neighbors;
    }

    public boolean isSameSpot(Point2 target) {
        return target.GridX == GridX && target.GridY == GridY;
    }

    public Point2 rotateClockwise() {
        double theta = Math.PI / 2.0;
        double currentRotation = Math.atan2(Y, X);
        currentRotation -= theta;
        float x = (float) Math.cos(currentRotation);
        float y = (float) Math.sin(currentRotation);
        return new Point2(x, y);
    }

    public Point2 rotate180() {
        return rotateClockwise().rotateClockwise();
    }

    public static float distanceSquared(Point2 source, Point2 target) {
        return (float) (Math.pow(source.PosX - target.PosX, 2) + Math.pow(source.PosY - target.PosY, 2));
    }

    @Override
    public String toString() {
        return "(gX,gY) - (posX,posY) extends  (" + GridX + "," + GridY + ") - (" + PosX + "," + PosY + ")";
    }
}
