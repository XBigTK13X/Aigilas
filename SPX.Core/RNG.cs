using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.Core
{
    public class RNG
    {
        public static readonly Random Rand = new Random();

        public static double Angle()
        {
            return RNG.Rand.Next(0, 360) * Math.PI / 180;
        }

        public static int Negative(int radius)
        {
            return RNG.Rand.Next(0, Math.Abs(radius) * 2) - Math.Abs(radius);
        }

        public static bool CoinFlip()
        {
            return RNG.Rand.Next(0, 2) == 1;
        }
    }
}
