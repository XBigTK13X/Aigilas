using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Management;
using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    class Floor:GameplayObject
    {
        public Floor(int gridX, int gridY)
        {
            Initialize(gridX, gridY, SpriteType.FLOOR,GameObjectType.FLOOR);
        }
    }
}
