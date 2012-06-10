using System.Collections.Generic;
namespace Aigilas.Creatures
{
    public class Generate
    {
        public static List<int> Randoms = new List<int>() { AigilasActorType.PEON, AigilasActorType.ZORB };
    }
    public class AigilasActorType
    {
        public const int MINION = 2;
        public const int PLAYER = 3;
        public const int ACID_NOZZLE = 4;
        public const int PEON = 5;
        public const int ZORB = 6;
        public const int NONPLAYER = 7;
        public const int DART_TRAP = 8;
        public const int WRATH = 9;
        public const int HAND = 10;
        public const int ENVY = 11;
        public const int PRIDE = 12;
        public const int SLOTH = 13;
        public const int GREED = 14;
        public const int GLUTTONY = 15;
        public const int LUST = 16;
        public const int SERPENT = 17;
        public const int BREAKING_WHEEL = 18;
    }
}