using System.Linq;
using OGUR.Management;
using OGUR.Sprites;
using OGUR.GameObjects;

namespace OGUR.Collision
{
    internal static class CoordVerifier
    {
        public static bool IsValid(float x, float y)
        {
            return (x > 0 && y > 0 && x < XnaManager.WindowWidth && y < XnaManager.WindowHeight);
        }

        public static bool IsValid(Point2 position)
        {
            return (position.X > 0 && position.Y > 0 && position.X < XnaManager.WindowWidth && position.Y < XnaManager.WindowHeight);
        }

        public static bool IsBlocked(Point2 target)
        {
            return GameplayObjectManager.IsLocationBlocked(target);
        }   

        public static bool Contains(Point2 target, GameObjectType type)
        {
            return GameplayObjectManager.AnyContains(target, type);
        }
    }
}