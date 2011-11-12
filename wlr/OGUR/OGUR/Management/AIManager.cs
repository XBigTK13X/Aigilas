using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Creatures;

namespace OGUR.Management
{
    public class AIManager
    {
        public static bool IsClosestPlayerNorth(Player source)
        {
            var target = GetClosest(source);
            if (target.GetLocation().PosY < source.GetLocation().PosY)
            {
                return true;
            }
            return false;
        }

        public static bool IsClosestPlayerSouth(Player source)
        {
            var target = GetClosest(source);
            if (target.GetLocation().PosY > source.GetLocation().PosY)
            {
                return true;
            }
            return false;
        }

        public static bool IsClosestPlayerEast(Player source)
        {
            var target = GetClosest(source);
            if (target.GetLocation().PosX > source.GetLocation().PosX)
            {
                return true;
            }
            return false;
        }

        public static bool IsClosestPlayerWest(Player source)
        {
            var target = GetClosest(source);
            if (target.GetLocation().PosX < source.GetLocation().PosX)
            {
                return true;
            }
            return false;
        }

        private static Player GetClosest(Player source)
        {
            var minDist = float.PositiveInfinity;
            Player result = null;
            foreach(Player o in GameplayObjectManager.GetCreatures(CreatureType.PLAYER))
            {
                var dist = Math.Abs(o.GetLocation().PosX - o.GetLocation().PosX) + Math.Abs(o.GetLocation().PosY - o.GetLocation().PosY);
                if (dist < minDist)
                {
                    result = o;
                }
            }
            return result;
        }
    }
}