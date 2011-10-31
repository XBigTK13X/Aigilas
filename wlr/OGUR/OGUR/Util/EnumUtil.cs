using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace OGUR.Util
{
    class EnumUtil
    {
        private static List<Enum> result = new List<Enum>();
        public static List<Enum> GetValues(Type enumType)
        {
            if (enumType.BaseType == typeof(Enum))
            {
                result.Clear();
                foreach(var info in enumType.GetFields(BindingFlags.Static | BindingFlags.Public))
                {
                    result.Add((Enum)info.GetValue(null));
                }
                return result;
            }
            else
              {
                 throw new Exception("Given type is not an Enum type");
              }
        }
    }
}
