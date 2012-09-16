package aigilas.dungeons;

import spx.core.Point2;
import spx.core.Settings;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public int Height, Width, X, Y, BottomSide, RightSide;
    public Point2 Center;
    public List<Point2> Corners = new ArrayList<Point2>();

    public Room(int height, int width, int x, int y) {
        Height = height;
        Width = width;
        X = x;
        Y = y;
        BottomSide = Y + Height;
        RightSide = X + Width;
        Corners.add(new Point2(X, Y));
        Corners.add(new Point2(RightSide, Y));
        Corners.add(new Point2(RightSide, BottomSide));
        Corners.add(new Point2(X, BottomSide));
        Center = new Point2(RightSide / 2, BottomSide / 2);
    }

    public boolean isBad() {
        if (BottomSide > Settings.get().tileMapHeight) {
            return true;
        }
        if (RightSide > Settings.get().tileMapWidth) {
            return true;
        }
        return false;
    }

    public boolean collides(Room target) {
        for (Point2 corner : target.Corners) {
            if (isPointInsideBoundingBox(corner)) {
                return true;
            }
        }
        for (Point2 corner : Corners) {
            if (target.isPointInsideBoundingBox(corner)) {
                return true;
            }
        }
        if (target.isPointInsideBoundingBox(Center)) {
            return true;
        }
        return isPointInsideBoundingBox(target.Center);
    }

    public boolean isPointInsideBoundingBox(Point2 target) {
        if (target.X > X && target.Y > Y && target.X < RightSide && target.Y < BottomSide) {
            return true;
        }
        return false;
    }
}
