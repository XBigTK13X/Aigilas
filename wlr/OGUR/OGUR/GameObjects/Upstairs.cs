using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    public class Upstairs : GameplayObject
    {
        public Upstairs(Point2 location)
        {
            Initialize(location, SpriteType.UPSTAIRS, GameObjectType.UPSTAIRS,Depth.Stairs);
        }
        public Location GetTargetLocation()
        {
            return Location.Depths;
        }

        ICreature player;
        public override void  Update()
        {
            player = GameplayObjectManager.GetTouchingPlayer(this);
            if(player!=null)
            {
                if (player.IsInteracting())
                {
                    player.PerformInteraction();
                    DungeonFactory.GetPreviousFloor(GetTargetLocation());
                }    
            }
        }
    }
}