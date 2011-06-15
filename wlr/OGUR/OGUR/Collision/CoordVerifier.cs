using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Management;
using OGUR.Sprites;

namespace OGUR.Collision
{
    static class CoordVerifier
    {
        static public bool IsValid(int x, int y)
        {
            return (x > 0 && y > 0 && x < XnaManager.WindowWidth && y < XnaManager.WindowHeight);
        }

        static public bool IsBlocked(int x, int y)
        {
            var target = new Point(x + SpriteInfo.Width / 2, y + SpriteInfo.Height / 2);
            return GameplayObjectManager.GetObjects().Where(tile => tile.Contains(target) && tile.IsBlocking()).Count() > 0;
        }
    }
}
