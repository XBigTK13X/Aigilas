using System;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Collision
{
    internal static class HitTest
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
            return IsClose(source.GetPosition().X, target.GetPosition().X, source.GetPosition().Y,
                           target.GetPosition().Y);
        }

        private static bool IsClose(float x1, float x2, float y1, float y2)
        {
            return CalculateDistance(x1,x2,y1,y2) <= 2*(SpriteInfo.Radius*SpriteInfo.Radius);
        }

        public static float GetDistance(GameplayObject source, GameplayObject target)
        {
            return CalculateDistance(source.GetPosition().X, target.GetPosition().X, source.GetPosition().Y,
                                     target.GetPosition().Y);
        }

        private static float CalculateDistance(float x1,float x2, float y1, float y2)
        {
            return (float)(Math.Pow((x1 - x2), 2) + Math.Pow((y1 - y2), 2));
        }
    }
}