using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Util
{
    public class StringStorage
    {
        private static readonly Dictionary<int, string> __slots = new Dictionary<int, string>();

        public static string Get(float value)
        {
            if (__slots.Keys.Count() == 0)
            {
                for (float ii = 0; ii < 1000; ii++)
                {
                    __slots.Add((int)ii, ii.ToString());
                }
            }
            if (__slots.ContainsKey((int)value))
            {
                return __slots[(int)value];
            }
            return value.ToString();
        }
    }
}
