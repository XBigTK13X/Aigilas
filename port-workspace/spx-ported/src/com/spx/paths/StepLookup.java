package com.spx.paths;import com.xna.wrapper.*;import java.util.*;import com.spx.core.*;
    class StepLookup
    {
        private static ArrayList<HashMap<Point2,Point2>> __lookups = new ArrayList<HashMap<Point2,Point2>>();
        private static int _index = 0;        private static final int __lookupCount = 1000;

        public static HashMap<Point2,Point2> Copy(HashMap<Point2,Point2> walk)
        {
            if (__lookups.size() == 0)
            {
                for (int ii = 0; ii < __lookupCount; ii++)
                {
                    __lookups.add(new HashMap<Point2,Point2>(200));
                }
            }
            _index = (_index + 1) % __lookups.size();
            __lookups.get(_index).clear();
            for(Point2 key:walk.keySet())
            {
                __lookups.get(_index).put(key,walk.get(key));
            }
            return __lookups.get(_index);
        }
    }
