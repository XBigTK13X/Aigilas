using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.Management;
using welikerogues.Collision;
using welikerogues.Sprites;

namespace welikerogues.GameObjects
{
    class Floor:GameplayObject
    {
        public Floor(int gridX, int gridY)
        {
            Initialize(gridX, gridY, SpriteType.FLOOR,GameObjectType.FLOOR);
        }
    }
}
