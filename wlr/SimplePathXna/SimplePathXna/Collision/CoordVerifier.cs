using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;
using welikerogues.Management;

namespace welikerogues.Collision
{
    static class CoordVerifier
    {
        static public bool IsValid(int x, int y)
        {
            return (x > 0 && y > 0 && x < XnaManager.WindowWidth && y < XnaManager.WindowHeight);
        }
    }
}
