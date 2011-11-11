using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace OGUR.Util
{
    class EnumUtil<T>
    {
        private static readonly List<T> s_results = new List<T>();

        public static List<T> GetValues()
        {
            if (s_results.Count()==0)
            {
                foreach (var info in typeof(T).GetFields(BindingFlags.Static | BindingFlags.Public))
                {
                    s_results.Add((T)info.GetValue(null));
                }
            }
            return s_results;
        }
    }
}
