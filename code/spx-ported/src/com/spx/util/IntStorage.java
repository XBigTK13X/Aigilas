package com.spx.util;import java.util.*;
    public class IntStorage
    {
        private static HashMap<Float, Integer> __slots = new HashMap<Float, Integer>();

        public static int Get(float value)
        {
            if (!__slots.containsKey(value))
            {
                __slots.put(value, (int)value);
            }
            return __slots.get(value);
        }
    }
