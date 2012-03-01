using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Paths
{
    class Walk
    {
        private static List<Point2>[] s_walks = new List<Point2>[1000];
        private static int m_index = 0;

        public static List<Point2> Copy(List<Point2> walk)
        {
            if (s_walks[0] == null)
            {
                for (int ii = 0; ii < s_walks.Count(); ii++)
                {
                    s_walks[ii] = new List<Point2>();
                }
            }
            m_index = (m_index + 1) % s_walks.Count();
            s_walks[m_index].Clear();
            foreach (var point in walk)
            {
                s_walks[m_index].Add(point);
            }
            return s_walks[m_index];
        }
    }
}
