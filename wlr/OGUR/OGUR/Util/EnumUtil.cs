using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace OGUR.Util
{
    class EnumUtil
    {   
        public static Enum[] GetValues(Type enumType)
        {
            if (enumType.BaseType == typeof(Enum))
            {
            FieldInfo[] info = enumType.GetFields(BindingFlags.Static | BindingFlags.Public);
            Enum[] values = new Enum[info.Length];
            for (int i=0; i<values.Length; ++i)
            {
              values[i] = (Enum)info[i].GetValue(null);
            }
            return values;
          }
          else
          {
             throw new Exception("Given type is not an Enum type");
          }
    }
    }
}
