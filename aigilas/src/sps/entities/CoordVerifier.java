package sps.entities;

import sps.bridge.EntityType;
import sps.core.Point2;
import sps.core.SpsConfig;
import sps.graphics.Renderer;

public class CoordVerifier {
    public static boolean isValid(Point2 position) {
        return (position.PosX >= 0 && position.PosY >= 0 && position.PosX < Renderer.get().VirtualWidth && position.PosY < Renderer.get().VirtualHeight && position.GridX < SpsConfig.get().tileMapWidth && position.GridY < SpsConfig.get().tileMapHeight);
    }

    public static boolean isBlocked(Point2 target)

    {
        return EntityManager.get().isLocationBlocked(target);
    }

    public static boolean contains(Point2 target, EntityType type)

    {
        return EntityManager.get().anyAt(target, type);
    }
}
