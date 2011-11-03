using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Util
{
    
    class StringSquisher
    {
        private static readonly StringBuilder builder = new StringBuilder(64,64);
        private static readonly Dictionary<StringBuilder, string> lookup = new Dictionary<StringBuilder, string>();

        public static string Build(params string[] textToSquish)
        {
            Clear();
            for (int ii = 0; ii < textToSquish.Count(); ii++)
            {
                builder.Append(textToSquish[ii]);
            }
            return Flush();
        }

        public static void Squish(params string[] textToSquish)
        {
            for (int ii = 0; ii < textToSquish.Count(); ii++)
            {
                builder.Append(textToSquish[ii]);
            }
        }

        public static void Clear()
        {
            builder.Remove(0, builder.Length);
        }

        public static string Flush()
        {
            return builder.ToString();
        }
    }
}
