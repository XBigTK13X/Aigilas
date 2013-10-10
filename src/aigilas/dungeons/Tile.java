package aigilas.dungeons;

import sps.bridge.EntityType;
import sps.core.Point2;


public class Tile {
    public final EntityType EntityType;
    public final Point2 Position;
    public final int X;
    public final int Y;

    public Tile(EntityType entityType, int x, int y) {
        EntityType = entityType;
        Position = new Point2(x, y);
        X = x;
        Y = y;
    }
}
