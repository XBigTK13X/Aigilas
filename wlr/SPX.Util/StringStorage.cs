using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Util
{
    public class StringStorage
    {
        private static readonly Dictionary<int, string> s_slots = new Dictionary<int, string>();

        public static string Get(float value)
        {
            if (s_slots.Keys.Count() == 0)
            {
                for (float ii = 0; ii < 1000; ii++)
                {
                    s_slots.Add((int)ii, ii.ToString());
                }
            }
            if (s_slots.ContainsKey((int)value))
            {
                return s_slots[(int)value];
            }
            return value.ToString();
        }
    }
}
