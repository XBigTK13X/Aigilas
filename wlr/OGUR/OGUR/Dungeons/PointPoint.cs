using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Dungeons
{
    class PointPoint
    {
        public readonly int X;
        public readonly int Y;
        private readonly bool _isHorizontal;

        public PointPoint(int x, int y, bool destroyHorizontal = false)
        {
            X = x;
            Y = y;
            _isHorizontal = destroyHorizontal;
        }
        public bool isHorizontal()
        {
            return _isHorizontal;
        }
    }
}
