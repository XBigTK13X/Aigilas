using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Paths
{
    class StepLookup
    {
        private static Dictionary<Point2,Point2>[] s_lookups = new Dictionary<Point2,Point2>[1000];
        private static int m_index = 0;

        public static Dictionary<Point2,Point2> Copy(Dictionary<Point2,Point2> walk)
        {
            if (s_lookups[0] == null)
            {
                for (int ii = 0; ii < s_lookups.Count(); ii++)
                {
                    s_lookups[ii] = new Dictionary<Point2,Point2>(200);
                }
            }
            m_index = (m_index + 1) % s_lookups.Count();
            s_lookups[m_index].Clear();
            foreach (var keyval in walk)
            {
                s_lookups[m_index].Add(keyval.Key,keyval.Value);
            }
            return s_lookups[m_index];
        }
    }
}
