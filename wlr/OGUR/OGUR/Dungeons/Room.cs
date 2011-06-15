using System.Collections.Generic;
using OGUR.Collision;

namespace OGUR.Dungeons
{
    public class Room
    {
        public int Height, Width, X, Y, BottomSide, RightSide;
        public Point Center;
        public List<Point> Corners = new List<Point>();

        public Room(int height, int width, int x, int y)
        {
            Height = height;
            Width = width;
            X = x;
            Y = y;
            BottomSide = Y + Height;
            RightSide = X + Width;
            Corners.Add(new Point(X, Y));
            Corners.Add(new Point(RightSide, Y));
            Corners.Add(new Point(RightSide, BottomSide));
            Corners.Add(new Point(X, BottomSide));
            Center = new Point(RightSide/2, BottomSide/2);
        }

        public bool IsBad()
        {
            if (BottomSide > DungeonManager.BlocksHigh)
            {
                return true;
            }
            if (RightSide > DungeonManager.BlocksWide)
            {
                return true;
            }
            return false;
        }

        public bool Collides(Room target)
        {
            foreach (Point targetCorner in target.Corners)
            {
                if (IsPointInsideBoundingBox(targetCorner))
                {
                    return true;
                }
            }
            foreach (Point corner in Corners)
            {
                if (target.IsPointInsideBoundingBox(corner))
                {
                    return true;
                }
            }
            if (target.IsPointInsideBoundingBox(Center))
            {
                return true;
            }
            if (IsPointInsideBoundingBox(target.Center))
            {
                return true;
            }
            return false;
        }

        public bool IsPointInsideBoundingBox(Point target)
        {
            if (target.X > X && target.Y > Y && target.X < RightSide && target.Y < BottomSide)
            {
                return true;
            }
            return false;
        }
    }
}