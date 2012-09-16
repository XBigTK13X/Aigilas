package spx.entities;

import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.SpxManager;

public class CoordVerifier {
    public static boolean isValid(Point2 position) {
        return (position.PosX >= 0 && position.PosY >= 0 && position.PosX < SpxManager.WindowWidth && position.PosY < SpxManager.WindowHeight);
    }

    public static boolean isBlocked(Point2 target)

    {
        return EntityManager.isLocationBlocked(target);
    }

    public static boolean contains(Point2 target, EntityType type)

    {
        return EntityManager.anyContains(target, type);
    }
}
