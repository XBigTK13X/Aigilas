using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    public class Upstairs : GameplayObject
    {
        public Upstairs(int x, int y)
        {
            Initialize(x, y, SpriteType.UPSTAIRS, GameObjectType.UPSTAIRS);
        }
    }
}