using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;

namespace OGUR.GameObjects
{
    class Downstairs:GameplayObject
    {
        public Downstairs(int x, int y)
        {
            Initialize(x, y, SpriteType.DOWNSTAIRS, GameObjectType.DOWNSTAIRS);
        }
        public override void Update()
        {
            foreach (Player player in GameplayObjectManager.GetObjects(GameObjectType.PLAYER))
            {
                if (Collision.HitTest.IsTouching(player, this))
                {
                    DungeonManager.GotoNext();
                    Console.WriteLine("DOWNSTAIRS");
                }
            }
        }
    }
}
