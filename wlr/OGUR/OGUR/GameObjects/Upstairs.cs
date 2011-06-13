using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;

namespace OGUR.GameObjects
{
    class Upstairs:GameplayObject
    {
        public Upstairs(int x, int y)
        {
            Initialize(x, y, SpriteType.UPSTAIRS, GameObjectType.UPSTAIRS);
        }
        public override void Update()
        {
            foreach (Player player in GameplayObjectManager.GetObjects(GameObjectType.PLAYER))
            {
                if (Collision.HitTest.IsTouching(player, this))
                {
                    DungeonManager.GotoPrevious();
                }
            }
        }
    }
}
