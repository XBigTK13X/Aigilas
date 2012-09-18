package aigilas.dungeons;

import spx.core.Point2;
import spx.core.Settings;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public final int Height;
    public final int Width;
    public final int X;
    public final int Y;
    public final int BottomSide;
    public final int RightSide;
    public final Point2 Center;
    public final List<Point2> Corners = new ArrayList<>();

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
        return BottomSide > Settings.get().tileMapHeight || RightSide > Settings.get().tileMapWidth;
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
        return target.isPointInsideBoundingBox(Center) || isPointInsideBoundingBox(target.Center);
    }

    public boolean isPointInsideBoundingBox(Point2 target) {
        return target.X > X && target.Y > Y && target.X < RightSide && target.Y < BottomSide;
    }
}
