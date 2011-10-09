using System.Collections.Generic;
namespace OGUR.Creatures
{
    public enum CreatureType
    {
        MINION,
        PLAYER,
        ACID_NOZZLE,
        GOBLIN,
        ZORB,
        NONPLAYER
    }
    public class Generate
    {
        public static List<CreatureType> Randoms = new List<CreatureType>() { CreatureType.GOBLIN, CreatureType.ZORB };
    }
}