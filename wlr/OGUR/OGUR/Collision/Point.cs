using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework;
using OGUR.Dungeons;
using OGUR.Sprites;

namespace OGUR.Collision
{
    public class Point2
    {
        private static readonly Point2 Zero = new Point2(0, 0);
        private static readonly float halfHeight = SpriteInfo.Height/2;
        private static readonly float halfWidth= SpriteInfo.Width / 2;

        public float X { get; private set; }
        public float Y { get; private set; }
        public float Weight { get; private set; }
        public int GridX { get; private set;}
        public int GridY { get; private set; }
        public float PosX { get; private set; }
        public float PosY { get; private set; }
        public float PosCenterX { get; private set; }
        public float PosCenterY { get; private set; }

        public Point2(float x, float y, int weight = 0)
        {
            SetX(x);
            SetY(y);
            Weight = weight;
        }

        public Point2(Point2 target)
        {
            SetX(target.X);
            SetY(target.Y);
            Weight = 0;
        }

        public Point2(Vector2  target)
        {
            SetX(target.X);
            SetY(target.Y);
            Weight = 0;
        }

        public void SetX(float xValue)
        {
            X = xValue;
            var isGrid = (X < DungeonFactory.BlocksWide);
            PosX = (isGrid) ? X * SpriteInfo.Width : X;
            PosCenterX = (isGrid) ? X * SpriteInfo.Width + halfWidth : X + halfWidth;
            GridX = (isGrid) ? (int)X : (int)(X / SpriteInfo.Width);
        }

        public void SetY(float yValue)
        {
            Y = yValue;
            var isGrid = (Y < DungeonFactory.BlocksHigh);
            PosY = (isGrid)?Y*SpriteInfo.Height:Y;
            PosCenterY = (isGrid)?Y * SpriteInfo.Height + halfHeight : Y+halfHeight;
            GridY = (isGrid) ? (int)Y : (int)(Y / SpriteInfo.Height);
        }

        public void SetWeight(float weight)
        {
            Weight = weight;
        }

        public bool IsZero()
        {
            return Equals(Zero);
        }

        public Point2 Multiply(float factor)
        {
            return new Point2(X*factor,Y*factor);
        }

        public static float CalculateDistanceSquared(Point2 source, Point2 target)
        {
            return Math.Abs(source.Y - target.Y) + Math.Abs(source.X - target.X);
        }

        public IList<Point2> GetNeighbors()
        {
            var result = new List<Point2>();
            for (var ii = -1; ii < 2; ii++)
            {
                for (var jj = -1; jj < 2; jj++)
                {
                    if (ii != 0 || jj != 0)
                    {
                        var next = new Point2(X + (ii * SpriteInfo.Width), Y + (jj * SpriteInfo.Height));
                        result.Add(next);
                    }
                }
            }
            return result;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj.GetType() == typeof (Point2) && Equals((Point2) obj);
        }

        private bool Equals(Point2 other)
        {
            return other.X.Equals(X) && other.Y.Equals(Y);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var result = X.GetHashCode();
                result = (result*397) ^ Y.GetHashCode();
                result = (result*397) ^ Weight.GetHashCode();
                return result;
            }
        }

        public override string ToString()
        {
            return String.Format("Point2: ({0},{1})", X, Y);
        }
    }
}