package spx.paths;

import spx.core.Point2;

class PathFactory {
    private static final Path[] __paths = new Path[50];
    private static int __pathIndex = 0;

    public static Path create(Point2 source, Point2 dest) {
        return getNext().reset(source, dest);
    }

    public static Path create(Path path) {
        return getNext().copy(path);
    }

    private static Path getNext() {
        if (__paths[0] == null) {
            for (int ii = 0; ii < __paths.length; ii++) {
                __paths[ii] = new Path();
            }
        }
        __pathIndex = (++__pathIndex) % __paths.length;
        return __paths[__pathIndex];
    }
}
