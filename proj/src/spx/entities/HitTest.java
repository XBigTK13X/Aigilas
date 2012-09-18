package spx.entities;

import spx.core.GameManager;
import spx.core.Point2;

public class HitTest {
    public static boolean isTouching(IEntity source, IEntity target)

    {
        return isClose(source, target);
    }

    private static boolean isClose(IEntity source, IEntity target) {
        return isClose(source.getLocation().PosX, target.getLocation().PosX, source.getLocation().PosY, target.getLocation().PosY);
    }

    private static boolean isClose(float x1, float x2, float y1, float y2) {
        return getDistanceSquare(x1, x2, y1, y2) < GameManager.SpriteRadius;
    }

    public static float getDistanceSquare(IEntity source, IEntity target)

    {
        return getDistanceSquare(source.getLocation().PosX, target.getLocation().PosX, source.getLocation().PosY, target.getLocation().PosY);
    }

    public static float getDistanceSquare(Point2 source, Point2 target)

    {
        return getDistanceSquare(source.PosX, target.PosX, source.PosY, target.PosY);
    }

    private static float getDistanceSquare(float x1, float x2, float y1, float y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
