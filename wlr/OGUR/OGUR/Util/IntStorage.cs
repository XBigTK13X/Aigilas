using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Util
{
    class IntStorage
    {
        private static readonly Dictionary<float, int> s_slots = new Dictionary<float, int>();

        public static int Get(float value)
        {
            if (!s_slots.ContainsKey(value))
            {
                s_slots.Add(value, (int)value);
            }
            return s_slots[value];
        }
    }
}
