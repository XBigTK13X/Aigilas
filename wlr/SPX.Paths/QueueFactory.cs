using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Paths
{
    class QueueFactory
    {
        private static readonly Queue<Path>[] __stock = new Queue<Path>[15];
        private static int __index = 0;

        public static Queue<Path> Create()
        {
            if (__stock[0] == null)
            {
                for (int ii = 0; ii < __stock.Length; ii++)
                {
                    __stock[ii] = new Queue<Path>();
                }
            }
            GetNext().Clear();
            return GetCurrent();
        }

        private static Queue<Path> GetNext()
        {
            __index = (++__index) % __stock.Length;
            return GetCurrent();
        }

        private static Queue<Path> GetCurrent()
        {
            return __stock[__index];
        }
    }
}
