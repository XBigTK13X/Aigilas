using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using WeLikeRogues.Management;
using WeLikeRogues.Collision;
using WeLikeRogues.Sprites;

namespace WeLikeRogues.GameObjects
{
    class Floor:GameplayObject
    {
        public Floor(int gridX, int gridY)
        {
            Initialize(gridX, gridY, SpriteType.FLOOR,GameObjectType.FLOOR);
        }
    }
}
