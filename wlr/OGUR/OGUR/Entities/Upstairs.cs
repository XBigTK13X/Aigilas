using OGUR.Creatures;
using OGUR.Dungeons;
using SPX.Entities;
using OGUR.Management;
using SPX.Core;
using System;
using System.Runtime.Serialization;

namespace OGUR.Entities
{
    [Serializable()]
    public class Upstairs : Entity
    {
        public Upstairs(SerializationInfo info, StreamingContext context):base(info, context){}
        public Upstairs(Point2 location)
        {
            Initialize(location, SpriteType.UPSTAIRS, OGUR.EntityType.UPSTAIRS,ZDepth.Stairs);
        }
        public int GetTargetLocation()
        {
            return Location.Depths;
        }

        ICreature player;
        public override void  Update()
        {
            player = EntityManager.GetTouchingCreature(this) as Player;
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