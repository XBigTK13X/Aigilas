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
    }
}
