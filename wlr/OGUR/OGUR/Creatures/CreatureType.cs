using System.Collections.Generic;
namespace OGUR.Creatures
{
    public enum CreatureType
    {
        MINION,
        PLAYER,
        ACID_NOZZLE,
        PEON,
        ZORB,
        NONPLAYER
    }
    public class Generate
    {
        public static List<CreatureType> Randoms = new List<CreatureType>() { CreatureType.PEON, CreatureType.ZORB };
    }
}