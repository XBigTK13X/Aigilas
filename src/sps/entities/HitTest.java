package sps.entities;

import sps.bridge.Sps;
import sps.core.Point2;

public class HitTest {
    public static boolean isTouching(Entity source, Entity target)

    {
        return isClose(source, target);
    }

    private static boolean isClose(Entity source, Entity target) {
        return isClose(source.getLocation().PosX, target.getLocation().PosX, source.getLocation().PosY, target.getLocation().PosY);
    }

    private static boolean isClose(float x1, float x2, float y1, float y2) {
        return getDistanceSquare(x1, x2, y1, y2) < Sps.SpriteRadius;
    }

    public static float getDistanceSquare(Entity source, Entity target)

    {
        return getDistanceSquare(source.getLocation().PosX, target.getLocation().PosX, source.getLocation().PosY, target.getLocation().PosY);
    }

    public static float getDistanceSquare(Point2 source, Point2 target)

    {
        return getDistanceSquare(source.GridX, target.GridX, source.GridY, target.GridY);
    }

    private static float getDistanceSquare(float x1, float x2, float y1, float y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
