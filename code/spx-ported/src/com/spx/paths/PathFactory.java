package com.spx.paths;import com.xna.wrapper.*;import java.util.*;import com.spx.core.*;
    class PathFactory
    {
        private static Path[] __paths = new Path[50];
        private static int __pathIndex = 0;

        public static Path Create(Point2 source,Point2 dest)
        {
            return GetNext().Reset(source, dest);
        }

        public static Path Create(Path path)
        {
            return GetNext().Copy(path);
        }

        private static Path GetNext()
        {
            if (__paths[0] == null)
            {
                for (int ii = 0; ii < __paths.length; ii++)
                {
                    __paths[ii] = new Path();
                }
            }
            __pathIndex = (++__pathIndex) % __paths.length;
            return __paths[__pathIndex];
        }
    }
