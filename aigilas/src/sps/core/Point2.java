package sps.core;

import java.util.ArrayList;
import java.util.List;

public class Point2 {
    public static final Point2 Zero = new Point2(0, 0);
    private static final float halfHeight = SpsConfig.get().spriteHeight / 2;
    private static final float halfWidth = SpsConfig.get().spriteWidth / 2;

    public float X;
    public float Y;
    public float Weight;
    public int GridX;
    public int GridY;
    public float PosX;
    public float PosY;
    public float PosCenterX;
    public float PosCenterY;

    public static final Point2[][] _locations = new Point2[SpsConfig.get().tileMapHeight][SpsConfig.get().tileMapWidth];

    public Point2() {
    }

    public Point2(float x, float y, int weight) {
        setX(x);
        setY(y);
        Weight = weight;
    }

    public Point2(Point2 target) {
        this(target.X, target.Y, 0);
    }

    public Point2(int x, int y) {
        this(x, y, 0);
    }

    public Point2(float x, float y) {
        this(x, y, 0);
    }

    public void reset(float x, float y) {
        setX(x);
        setY(y);
    }

    public void reset(float x, float y, boolean isGrid) {
        if (isGrid) {
            setAdjustedX(x);
            setAdjustedY(y);
        }
        else {
            setRawX(x);
            setRawY(y);
        }
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

    public void setX(float x) {
        X = x;
        boolean isGrid = (Math.abs(X) < SpsConfig.get().tileMapWidth);
        if (isGrid) {
            setAdjustedX(x);
        }
        else {
            setRawX(x);
        }
    }

    private void setRawX(float x) {
        X = x;
        PosX = X;
        PosCenterX = PosX + halfWidth;
        GridX = (int) (X / SpsConfig.get().spriteWidth);
    }

    private void setAdjustedX(float x) {
        X = x;
        PosX = X * SpsConfig.get().spriteWidth;
        PosCenterX = PosX + halfWidth;
        GridX = (int) X;
    }

    public void setY(float y) {
        Y = y;
        boolean isGrid = (Math.abs(Y) < SpsConfig.get().tileMapHeight);
        if (isGrid) {
            setAdjustedY(y);
        }
        else {
            setRawY(y);
        }
    }

    private void setRawY(float y) {
        Y = y;
        PosY = Y;
        PosCenterY = PosY + halfWidth;
        GridY = (int) (Y / SpsConfig.get().spriteHeight);
    }

    private void setAdjustedY(float y) {
        Y = y;
        PosY = Y * SpsConfig.get().spriteHeight;
        PosCenterY = PosY + halfHeight;
        GridY = (int) Y;
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

    private static final List<Point2> _neighbors = new ArrayList<Point2>();

    public List<Point2> getNeighbors() {
        if (_locations[0][0] == null) {
            for (int ii = 0; ii < SpsConfig.get().tileMapHeight; ii++) {
                for (int jj = 0; jj < SpsConfig.get().tileMapWidth; jj++) {
                    _locations[ii][jj] = new Point2(jj, ii);
                }
            }
        }
        _neighbors.clear();
        for (int ii = -1; ii < 2; ii++) {
            for (int jj = -1; jj < 2; jj++) {
                if ((ii != 0 || jj != 0) && (GridX + jj >= 0 && GridY + ii >= 0) && (GridX + jj < SpsConfig.get().tileMapWidth && GridY + ii < SpsConfig.get().tileMapHeight)) {
                    _neighbors.add(_locations[GridY + ii][GridX + jj]);
                }
            }
        }
        return _neighbors;
    }

    public boolean isSameSpot(Point2 target) {
        return target.GridX == GridX && target.GridY == GridY;
    }

    public Point2 rotate() {
        return rotate(45);
    }

    public Point2 rotate(int degrees) {
        double theta = degrees * Math.PI / 180;
        double currentRotation = Math.atan2(Y, X);
        currentRotation -= theta;
        float x = (int) Math.round(Math.cos(currentRotation));
        float y = (int) Math.round(Math.sin(currentRotation));
        return new Point2(x, y);
    }

    public static float distanceSquared(Point2 source, Point2 target) {
        return (float) (Math.pow(source.PosX - target.PosX, 2) + Math.pow(source.PosY - target.PosY, 2));
    }

    @Override
    public String toString() {
        return "(X,Y) - (gX,gY) - (posX,posY):  (" + X + "," + Y + ") - (" + GridX + "," + GridY + ") - (" + PosX + "," + PosY + ")";
    }
}
