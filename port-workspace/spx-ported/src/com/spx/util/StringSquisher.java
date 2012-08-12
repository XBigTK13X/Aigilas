package com.spx.util;import com.xna.wrapper.*;import java.util.*;

    public class StringSquisher
    {
        private static StringBuilder builder = new StringBuilder(64);
        private static HashMap<StringBuilder, String> lookup = new HashMap<StringBuilder, String>();

        public static String Build(String...textToSquish)
        {
            Clear();
            for (int ii = 0; ii < textToSquish.length; ii++)
            {
                builder.append(textToSquish[ii]);
            }
            return Flush();
        }

        public static void Squish(String...textToSquish)
        {
            for (int ii = 0; ii < textToSquish.length; ii++)
            {
                builder.append(textToSquish[ii]);
            }
        }

        public static void Clear()
        {
            builder.setLength(0);
        }

        public static String Flush()
        {
            return builder.toString();
        }
    }
