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

        public float X, Y, Weight;

        public Point2(float x, float y, int weight = 0)
        {
            X = x;
            Y = y;
            Weight = weight;
        }

        public Point2(Point2 target)
        {
            X = target.X;
            Y = target.Y;
            Weight = 0;
        }

        public Point2(Vector2  target)
        {
            X = target.X;
            Y = target.Y;
            Weight = 0;
        }

        public bool IsZero()
        {
            var isZero = Equals(Zero);
            if(isZero)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public int GridX()
        {
            if(X<DungeonFactory.BlocksWide)
            {
                return (int)X;
            }
            return (int) (X/SpriteInfo.Width);
        }

        public int GridY()
        {
            if (Y < DungeonFactory.BlocksWide)
            {
                return (int)Y;
            }
            return (int)(Y / SpriteInfo.Height);
        }

        public Point2 Multiply(float factor)
        {
            return new Point2(X*factor,Y*factor);
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

        public static float CalculateDistanceSquared(Point2 source, Point2 target)
        {
            return Math.Abs(source.Y - target.Y) + Math.Abs(source.X - target.X);
        }

        public IList<Point2> GetNeighbors()
        {
            var result = new List<Point2>();
            for (var ii = -1; ii < 2; ii++)
            {
                for(var jj = -1;jj<2;jj++)
                {
                    if(ii!=0||jj!=0)
                    {
                        var next = new Point2(X + (ii*SpriteInfo.Width), Y + (jj*SpriteInfo.Height));
                        result.Add(next);        
                    }
                }
            }
            return result;
        }
    }
}