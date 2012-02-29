using System;
using SPX.Entities;

namespace OGUR.Collision
{
    public static class HitTest
    {
        public static bool IsTouching(GameplayObject source, GameplayObject target)
        {
            if (IsClose(source, target))
            {
                return true;
            }
            return false;
        }

        private static bool IsClose(GameplayObject source, GameplayObject target)
        {
            return IsClose(source.GetLocation().PosX, target.GetLocation().PosX, source.GetLocation().PosY,
                           target.GetLocation().PosY);
        }

        private static bool IsClose(float x1, float x2, float y1, float y2)
        {
            return GetDistanceSquare(x1,x2,y1,y2) < SpriteInfo.Radius;
        }

        public static float GetDistanceSquare(GameplayObject source, GameplayObject target)
        {
            return GetDistanceSquare(source.GetLocation().PosX, target.GetLocation().PosX, source.GetLocation().PosY,
                                     target.GetLocation().PosY);
        }
        public static float GetDistanceSquare(Point2 source, Point2 target)
        {
            return GetDistanceSquare(source.PosX, target.PosX, source.PosY, target.PosY);
        }
        private static float GetDistanceSquare(float x1,float x2, float y1, float y2)
        {
            return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        }
    }
}