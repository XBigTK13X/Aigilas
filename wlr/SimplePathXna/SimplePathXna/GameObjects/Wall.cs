using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.Sprites;

namespace welikerogues.GameObjects
{
    class Wall:GameplayObject
    {
        public Wall(int x, int y)
        {
            Initialize(x, y, SpriteType.WALL, GameObjectType.WALL);
        }
    }
}
