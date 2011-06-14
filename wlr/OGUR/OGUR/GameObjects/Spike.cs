using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Management;
using OGUR.Collision;
using OGUR.Sprites;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    class Spike:GameplayObject
    {
        public Spike(int x, int y)
        {
            Initialize(x, y, SpriteType.SPIKE,GameObjectType.SPIKE);
        }
        public override void Update()
        {
            base.Update();
            if (null != GameplayObjectManager.GetObject(CreatureType.PLAYER))
            {
                if (HitTest.IsTouching(this, GameplayObjectManager.GetObject(CreatureType.PLAYER)))
                {
                    GameplayObjectManager.GetObject(CreatureType.PLAYER).SetInactive();
                }
            }
        }
    }
}
