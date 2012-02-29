using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;

namespace OGUR.Paths
{
    class PathFactory
    {
        private static readonly Path[] s_paths = new Path[50];
        private static int s_pathIndex = 0;

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
            if (s_paths[0] == null)
            {
                for (int ii = 0; ii < s_paths.Length; ii++)
                {
                    s_paths[ii] = new Path();
                }
            }
            s_pathIndex = (++s_pathIndex) % s_paths.Length;
            return s_paths[s_pathIndex];
        }
    }
}
