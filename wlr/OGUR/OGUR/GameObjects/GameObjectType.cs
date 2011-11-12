using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.GameObjects
{
    public class GameObjectType
    {
        public const int FLOOR = 0;
        public const int WALL = 1;
        public const int UPSTAIRS = 2;
        public const int DOWNSTAIRS = 3;
        public const int CREATURE = 4;
        public const int ITEM = 5;
        public const int SKILL_EFFECT = 6;
        public const int SKILL_EFFECT_GENERATOR = 7;
        public const int ALTAR = 8;

        public static readonly List<int> Values = new List<int>()
        {
            FLOOR,
            WALL,
            UPSTAIRS,
            DOWNSTAIRS,
            CREATURE,
            ITEM,
            SKILL_EFFECT,
            SKILL_EFFECT_GENERATOR,
            ALTAR
        };
    }
}