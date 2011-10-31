using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace OGUR.Util
{
    class EnumUtil
    {
        private static readonly Dictionary<Type, List<Enum>> s_results = new Dictionary<Type, List<Enum>>();

        public static List<Enum> GetValues(Type enumType)
        {
            if (!s_results.ContainsKey(enumType))
            {
                if (enumType.BaseType == typeof(Enum))
                {
                    s_results.Add(enumType,new List<Enum>());
                    foreach (var info in enumType.GetFields(BindingFlags.Static | BindingFlags.Public))
                    {
                        s_results[enumType].Add((Enum)info.GetValue(null));
                    }
                }
                else
                {
                    throw new Exception("Given type is not an Enum type");
                }
            }
            return s_results[enumType];
        }
    }
}
