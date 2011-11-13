using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Paths
{
    class QueueFactory
    {
        private static readonly Queue<Path>[] s_stock = new Queue<Path>[15];
        private static int s_index = 0;

        public static Queue<Path> Create()
        {
            if (s_stock[0] == null)
            {
                for (int ii = 0; ii < s_stock.Length; ii++)
                {
                    s_stock[ii] = new Queue<Path>();
                }
            }
            GetNext().Clear();
            return GetCurrent();
        }

        private static Queue<Path> GetNext()
        {
            s_index = (++s_index) % s_stock.Length;
            return GetCurrent();
        }

        private static Queue<Path> GetCurrent()
        {
            return s_stock[s_index];
        }
    }
}
