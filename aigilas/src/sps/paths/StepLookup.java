package sps.paths;

import sps.core.Point2;

import java.util.ArrayList;
import java.util.HashMap;

class StepLookup {
    private static final ArrayList<HashMap<Point2, Point2>> __lookups = new ArrayList<>();
    private static int _index = 0;
    private static final int __lookupCount = 1000;

    public static HashMap<Point2, Point2> copy(HashMap<Point2, Point2> walk) {
        if (__lookups.size() == 0) {
            for (int ii = 0; ii < __lookupCount; ii++) {
                __lookups.add(new HashMap<Point2, Point2>(200));
            }
        }
        _index = (_index + 1) % __lookups.size();
        __lookups.get(_index).clear();
        for (Point2 key : walk.keySet()) {
            __lookups.get(_index).put(key, walk.get(key));
        }
        return __lookups.get(_index);
    }
}
