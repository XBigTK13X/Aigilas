using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace OGUR.GameObjects
{
    public class Elements
    {
        public const int NORMAL = 0;
        public const int FIRE = 1;
        public const int WATER = 2;
        public const int EARTH = 3;
        public const int AIR = 4;
        public const int LIGHT = 5;
        public const int DARK = 6;
        public const int PHYSICAL = 7;
        public const int MENTAL = 8;

        public static readonly int[] Values = 
        {
            NORMAL,
            FIRE,
            WATER,
            EARTH,
            AIR,
            LIGHT,
            DARK,
            PHYSICAL,
            MENTAL
        };

        public static readonly Color[] Colors =
        {
            Color.Gray,
            Color.Black,
            Color.White,
            Color.Red,
            Color.Green,
            Color.Blue,
            Color.Purple,
            Color.Yellow,
            Color.Orange
        };

    }
}
