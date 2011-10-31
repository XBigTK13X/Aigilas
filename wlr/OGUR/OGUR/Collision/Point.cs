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

        public static Point2[] m_rotateTargets = 
            {
                new Point2(1, 0),
                new Point2(1, 1),
                new Point2(0, 1),
                new Point2(0,-1),
                new Point2(-1,-1),
                new Point2(-1, 0),
                new Point2(-1, 1),
                new Point2(1, -1)
            };

        public static Point2[,] m_locations;

        public Point2(float x, float y, int weight = 0)
        {
            SetX(x);
            SetY(y);
            Weight = weight;
        }

        public void Reset(float x, float y)
        {
            SetX(x);
            SetY(y);
        }

        public Point2(Point2 target)
        {
            SetX(target.X);
            SetY(target.Y);
            Weight = 0;
        }

        public Point2(Vector2 target)
        {
            SetX(target.X);
            SetY(target.Y);
            Weight = 0;
        }

        public void Copy(Point2 point)
        {
            SetX(point.X);
            SetY(point.Y);
        }

        public override int GetHashCode()
        {
            return GridX + 1000*GridY;
        }

        public override bool Equals(object obj)
        {
            if (obj.GetType()==typeof(Point2))
            {
                var target = (Point2) obj;
                return target.GridX == GridX && target.GridY == GridY;
            }
            return false;
        }

        public void SetX(float xValue)
        {
            X = xValue;
            var isGrid = (X < DungeonFactory.BlocksWide);
            PosX = (isGrid) ? X * SpriteInfo.Width : X;
            PosCenterX = PosX + halfWidth;
            GridX = (isGrid) ? (int)X : (int)(X / SpriteInfo.Width);
        }

        public void SetY(float yValue)
        {
            Y = yValue;
            var isGrid = (Y < DungeonFactory.BlocksHigh);
            PosY = (isGrid)?Y*SpriteInfo.Height:Y;
            PosCenterY = PosY+halfHeight;
            GridY = (isGrid) ? (int)Y : (int)(Y / SpriteInfo.Height);
        }

        public void SetWeight(float weight)
        {
            Weight = weight;
        }

        public bool IsZero()
        {
            return X == 0 && Y == 0;
        }

        public static float CalculateDistanceSquared(Point2 source, Point2 target)
        {
            return (float) (Math.Pow(source.PosY - target.PosY, 2) + Math.Pow(source.PosX - target.PosX, 2));
        }

        private static List<Point2> m_neighbors = new List<Point2>();
        public List<Point2> GetNeighbors()
        {
            if (m_locations == null)
            {
                m_locations = new Point2[DungeonFactory.BlocksHigh, DungeonFactory.BlocksWide];
                for (int ii = 0; ii < DungeonFactory.BlocksHigh; ii++)
                {
                    for (int jj = 0; jj < DungeonFactory.BlocksWide; jj++)
                    {
                        m_locations[ii,jj] = new Point2(jj, ii);
                    }
                }
            }
            m_neighbors.Clear();
            for (var ii = -1; ii < 2; ii++)
            {
                for (var jj = -1; jj < 2; jj++)
                {
                    if (ii != 0 || jj != 0)
                    {
                        m_neighbors.Add(m_locations[GridY + ii,GridX + jj]);
                    }
                }
            }
            return m_neighbors;
        }

        public Point2 RotateClockwise()
        {
            if (GridX == 1)
            {
                if (GridY == -1)
                {
                    return m_rotateTargets[0];
                }
                if (GridY == 0)
                {
                    return m_rotateTargets[1];
                }
                if (GridY == 1)
                {
                    return m_rotateTargets[2];
                }
            }
            if (GridX == -1)
            {
                if (GridY == -1)
                {
                    return m_rotateTargets[3];
                }
                if (GridY == 0)
                {
                    return m_rotateTargets[4];
                }
                if (GridY == 1)
                {
                    return m_rotateTargets[5];
                }
            }
            if (GridX == 0)
            {
                if (GridY == 1)
                {
                    return m_rotateTargets[6];
                }
                if (GridY == -1)
                {
                    return m_rotateTargets[7];
                }
            }
            return Zero;
            /*
             * This is getting close, but the flipped Y coord is ticking me off.
            var theta = Math.PI / 4f;
            var currentRotation = Math.Atan2(-Y, X);
            Console.WriteLine(currentRotation);
            currentRotation -= theta;
            var x = (float)Math.Cos(currentRotation);
            var y = (float)Math.Sin(currentRotation);
            if(x!=0)
            {
                x = (1 / (Math.Abs(x))) * x;
            }
            if (y != 0)
            {
                y = (1 / (Math.Abs(y))) * y;
            }
            var result = new Point2(x,y);
            Console.WriteLine(result);
            return result;
             * */
        }

        public override string ToString()
        {
            return String.Format("Point2: ({0},{1})", X, Y);
        }

        public static float DistanceSquared(Point2 source, Point2 target)
        {
            return (float)(Math.Pow(source.PosX - target.PosX, 2) + Math.Pow(source.PosY - target.PosY, 2));
        }
    }
}