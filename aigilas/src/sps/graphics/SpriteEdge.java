package sps.graphics;

import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.EntityManager;

//Using this class requires 6 different sprites in this horizontal order:
//Direction is the side on which a neighbor exists
//None, Left, Up, LeftUp, LeftRight, and LeftUpRight, and All
public enum SpriteEdge {
    None(0, 0, 0),
    Left(1, 1, 0),
    Up(2, 1, -90),
    LeftUp(3, 2, 0),
    Right(4, 1, 180),
    LeftRight(5, 3, 0),
    UpRight(6, 2, -90),
    LeftUpRight(7, 4, 0),
    Down(8, 1, 90),
    DownLeft(9, 2, 90),
    DownUp(10, 3, -90),
    DownLeftUp(11, 4, 90),
    DownRight(12, 2, 180),
    DownLeftRight(13, 4, 180),
    DownUpRight(14, 4, -90),
    DownLeftUpRight(15, 5, 0);

    private int bitwiseIndex;
    public final int Frame;
    public final int Rotation;

    private SpriteEdge(int bitwiseIndex, int frame, int rotation) {
        this.bitwiseIndex = bitwiseIndex;
        Frame = frame;
        Rotation = rotation;
    }

    private static SpriteEdge[] edges = new SpriteEdge[16];
    private static int bIndex;

    public static SpriteEdge determine(EntityType type, Point2 location) {
        if (edges[0] == null) {
            for (SpriteEdge edge : values()) {
                edges[edge.bitwiseIndex] = edge;
            }
        }

        bIndex = 0;
        if (EntityManager.get().anyAt(location.add(-1, 0), type)) {
            bIndex += 1;
        }
        if (EntityManager.get().anyAt(location.add(0, 1), type)) {
            bIndex += 2;
        }
        if (EntityManager.get().anyAt(location.add(1, 0), type)) {
            bIndex += 4;
        }
        if (EntityManager.get().anyAt(location.add(0, -1), type)) {
            bIndex += 8;
        }
        return edges[bIndex];
    }
}
