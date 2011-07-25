using System;

namespace OGUR.Collision
{
    public class Point
    {
        public int X, Y,Weight;

        public Point(int x, int y,int weight = 0)
        {
            X = x;
            Y = y;
            Weight = weight;
        }
    }

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

        public bool IsZero()
        {
            var isZero = Equals(Zero);
            if(isZero)
            {
                return true;
            }
            else
            {
                Console.WriteLine(this);
                return false;
            }
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
    }
}