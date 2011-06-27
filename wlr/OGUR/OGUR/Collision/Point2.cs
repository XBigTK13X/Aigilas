using Microsoft.Xna.Framework;
using OGUR.Sprites;

namespace OGUR.Collision
{
    public class Point2
    {
        public int X, Y, Weight, ScreenX, ScreenY;

        public static Point2 Zero = new Point2(0,0);

        public Point2(int x, int y,int weight = 0)
        {
            X = x;
            Y = y;
            ScreenX = X * SpriteInfo.Width;
            ScreenY = Y * SpriteInfo.Height;
            Weight = weight;
        }
        
        public static Point2 operator +(Point2 p1, Point2 p2)
        {
            return new Point2(p1.X+p2.X,p1.Y+p2.Y);
        }

        public static Point2 operator -(Point2 p1, Point2 p2)
        {
            return new Point2(p1.X - p2.X, p1.Y - p2.Y);
        }

        public Vector2 ToVec2()
        {
            return new Vector2(X,Y);
        }
    }
}