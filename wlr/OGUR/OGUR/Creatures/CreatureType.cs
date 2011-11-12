using System.Collections.Generic;
namespace OGUR.Creatures
{
    public class Generate
    {
        public static List<int> Randoms = new List<int>() { CreatureType.PEON, CreatureType.ZORB };
    }
    public class CreatureType
    {
        public const int MINION = 0;
        public const int PLAYER = 1;
        public const int ACID_NOZZLE = 2;
        public const int PEON = 3;
        public const int ZORB = 4;
        public const int NONPLAYER = 5;

        public static readonly List<int> Values = new List<int>()
        {
            MINION,
            PLAYER,
            ACID_NOZZLE,
            PEON,
            ZORB,
            NONPLAYER
        };
    }
}