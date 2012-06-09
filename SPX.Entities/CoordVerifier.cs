using System.Linq;
using SPX.Core;
using SPX.Sprites;
using SPX.Entities;

namespace SPX.Entities
{
    public static class CoordVerifier
    {
        public static bool IsValid(Point2 position)
        {
            return (position.PosX >= 0 && position.PosY >= 0 && position.PosX < XnaManager.WindowWidth && position.PosY < XnaManager.WindowHeight);
        }

        public static bool IsBlocked(Point2 target)
        {
            return EntityManager.IsLocationBlocked(target);
        }   

        public static bool Contains(Point2 target, int type)
        {
            return EntityManager.AnyContains(target, type);
        }
    }
}