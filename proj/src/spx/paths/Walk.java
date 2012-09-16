package spx.paths;

import spx.core.Point2;

import java.util.ArrayList;
import java.util.List;

class Walk {
    private static ArrayList<List<Point2>> __walks = new ArrayList<List<Point2>>();
    private static int _index = 0;
    private static final int __walkCount = 1000;

    public static List<Point2> copy(List<Point2> walk) {
        if (__walks.size() == 0) {
            for (int ii = 0; ii < __walkCount; ii++) {
                __walks.add(new ArrayList<Point2>());
            }
        }
        _index = (_index + 1) % __walks.size();
        __walks.get(_index).clear();
        for (Point2 point : walk) {
            __walks.get(_index).add(point);
        }
        return __walks.get(_index);
    }
}
