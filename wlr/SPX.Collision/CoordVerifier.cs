using System.Linq;
using OGUR.Management;
using OGUR.Sprites;
using OGUR.GameObjects;

namespace OGUR.Collision
{
    public static class CoordVerifier
    {
        public static bool IsValid(Point2 position)
        {
            return (position.PosX >= 0 && position.PosY >= 0 && position.PosX < XnaManager.WindowWidth && position.PosY < XnaManager.WindowHeight);
        }

        public static bool IsBlocked(Point2 target)
        {
            return GameplayObjectManager.IsLocationBlocked(target);
        }   

        public static bool Contains(Point2 target, int type)
        {
            return GameplayObjectManager.AnyContains(target, type);
        }
    }
}