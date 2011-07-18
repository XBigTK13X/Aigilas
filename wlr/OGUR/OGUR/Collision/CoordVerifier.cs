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

        public static bool IsBlocked(float x, float y)
        {
            var target = new Point2(x + SpriteInfo.Width/2, y + SpriteInfo.Height/2);
            return
                GameplayObjectManager.GetObjects().Where(tile => tile.Contains(target) && tile.IsBlocking()).Count() > 0;
        }
    }
}