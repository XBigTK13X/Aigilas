using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Entities;

namespace Agilas
{
    public class EntityType:SPX.Entities.EntityType
    {
        public const int WALL = 2;
        public const int UPSTAIRS = 3;
        public const int DOWNSTAIRS = 4;
        public const int ITEM = 5;
        public const int SKILL_EFFECT = 6;
        public const int SKILL_EFFECT_GENERATOR = 7;
        public const int ALTAR = 8;
        public const int COMBO_MARKER = 9;
    }
}