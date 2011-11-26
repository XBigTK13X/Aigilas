using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using System.Diagnostics;

namespace OGUR.Tests
{
    class TestManager
    {
        public static void Run(bool actuallyRun=true)
        {
            if (actuallyRun)
            {
                TestPointRotation();
            }
        }
        private static void TestPointRotation()
        {
            var location = new Point2(1, 0);
            for (int ii = 0; ii < 8; ii++)
            {
                location.Copy(location.RotateClockwise());
            }
            Debug.Assert(location.GridX == 1 && location.GridY == 0);
        }
    }
}
