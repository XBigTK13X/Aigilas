using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Sprites;

namespace OGUR.Management
{
    class SpriteInitializer: ISpriteInitializer
    {
        private SpriteDefinition Make(int type, int index, int frames)
        {
            return new SpriteDefinition(type, index, frames);
        }
        public ICollection<SpriteDefinition> GetSprites()
        {
            return new List<SpriteDefinition>()
            {
                Make(SpriteType.EMPTY, 0, 1),
                Make(SpriteType.PLAYER_STAND,1, 2),
                Make(SpriteType.FLOOR, 2, 1),
                Make(SpriteType.WALL, 3, 1),
                Make(SpriteType.UPSTAIRS,4, 1),
                Make(SpriteType.DOWNSTAIRS,5, 1),            
                Make(SpriteType.CREATURE,6, 1),
                Make(SpriteType.ITEM,7, 1),
                Make(SpriteType.SKILL_EFFECT,8, 1),
                Make(SpriteType.ALTAR,9, 1),
                Make(SpriteType.ZORB,10,1),
                Make(SpriteType.MINION,11,1),
                Make(SpriteType.COMBO_MARKER,13,1),
                Make(SpriteType.WRATH,14,1),
                Make(SpriteType.HAND,15,1)
            };
        }
    }
}
