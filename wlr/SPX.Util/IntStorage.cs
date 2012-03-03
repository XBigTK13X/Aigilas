using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Util
{
    public class IntStorage
    {
        private static readonly Dictionary<float, int> __slots = new Dictionary<float, int>();

        public static int Get(float value)
        {
            if (!__slots.ContainsKey(value))
            {
                __slots.Add(value, (int)value);
            }
            return __slots[value];
        }
    }
}
