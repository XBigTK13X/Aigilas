using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;

namespace OGUR.Management
{
    class AIManager
    {
        private static List<Player> s_players = new List<Player>();

        public static void Add(Player player)
        {
            s_players.Add(player);
        }

        public static bool IsClosestPlayerNorth(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetPosition().Y < source.GetPosition().Y)
            {
                return true;
            }
            return false;
        }

        public static bool IsClosestPlayerSouth(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetPosition().Y > source.GetPosition().Y)
            {
                return true;
            }
            return false;
        }

        public static bool IsClosestPlayerEast(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetPosition().X > source.GetPosition().X)
            {
                return true;
            }
            return false;
        }

        public static bool IsClosestPlayerWest(Player source)
        {
            Player target = GetClosest(source);
            if (target.GetPosition().X < source.GetPosition().X)
            {
                return true;
            }
            return false;
        }

        private static Player GetClosest(Player source)
        {
            List<float> s_distances = new List<float>();
            foreach (Player target in s_players)
            {               
                s_distances.Add(Math.Abs(target.GetPosition().X-source.GetPosition().X)+Math.Abs(target.GetPosition().Y-source.GetPosition().Y));   
            }
            float leastDistance = float.PositiveInfinity;
            Player result = null;
            for (int ii = 0; ii < s_distances.Count(); ii++)
            {
                if (s_distances[ii] != 0)
                {
                    if (s_distances[ii] < leastDistance)
                    {
                        leastDistance = s_distances[ii];
                        result = s_players[ii];
                    }
                }
            }
            return result;
        }
    }
}
