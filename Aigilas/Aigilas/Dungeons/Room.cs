using System.Collections.Generic;
using System.Linq;
using SPX.Entities;
using SPX.Core;

namespace Agilas.Dungeons
{
    public class Room
    {
        public int Height, Width, X, Y, BottomSide, RightSide;
        public Point2 Center;
        public List<Point2> Corners = new List<Point2>();

        public Room(int height, int width, int x, int y)
        {
            Height = height;
            Width = width;
            X = x;
            Y = y;
            BottomSide = Y + Height;
            RightSide = X + Width;
            Corners.Add(new Point2(X, Y));
            Corners.Add(new Point2(RightSide, Y));
            Corners.Add(new Point2(RightSide, BottomSide));
            Corners.Add(new Point2(X, BottomSide));
            Center = new Point2(RightSide/2, BottomSide/2);
        }

        public bool IsBad()
        {
            if (BottomSide > DungeonFactory.BlocksHigh)
            {
                return true;
            }
            if (RightSide > DungeonFactory.BlocksWide)
            {
                return true;
            }
            return false;
        }

        public bool Collides(Room target)
        {
            if (target.Corners.Any(targetCorner => IsPointInsideBoundingBox(targetCorner)))
            {
                return true;
            }
            if (Corners.Any(corner => target.IsPointInsideBoundingBox(corner)))
            {
                return true;
            }
            if (target.IsPointInsideBoundingBox(Center))
            {
                return true;
            }
            return IsPointInsideBoundingBox(target.Center);
        }

        public bool IsPointInsideBoundingBox(Point2 target)
        {
            if (target.X > X && target.Y > Y && target.X < RightSide && target.Y < BottomSide)
            {
                return true;
            }
            return false;
        }
    }
}