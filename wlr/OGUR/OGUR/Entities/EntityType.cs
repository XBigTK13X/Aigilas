using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Entities;

namespace OGUR
{
    public class EntityType:SPX.Entities.EntityType
    {
        public const int FLOOR = 2;
        public const int WALL = 3;
        public const int UPSTAIRS = 4;
        public const int DOWNSTAIRS = 5;
        public const int CREATURE = 6;
        public const int ITEM = 7;
        public const int SKILL_EFFECT = 8;
        public const int SKILL_EFFECT_GENERATOR = 9;
        public const int ALTAR = 10;
    }
}