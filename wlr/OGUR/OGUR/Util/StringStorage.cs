using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Util
{
    class StringStorage
    {
        private static readonly Dictionary<float, string> s_slots = new Dictionary<float, string>();

        public static string Get(float value)
        {
            if (s_slots.Keys.Count() == 0)
            {
                for (float ii = 0; ii < 1000; ii++)
                {
                    s_slots.Add(ii, ii.ToString());
                }
            }
            if (s_slots.ContainsKey(value))
            {
                return s_slots[value];
            }
            return "**";
        }
    }
}
