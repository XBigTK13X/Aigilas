using Aigilas.Creatures;
using Aigilas.Dungeons;
using SPX.Entities;
using Aigilas.Management;
using SPX.Core;

namespace Aigilas.Entities
{
    public class Upstairs : Entity
    {
        public Upstairs(Point2 location)
        {
            Initialize(location, SpriteType.UPSTAIRS, Aigilas.EntityType.UPSTAIRS,ZDepth.Stairs);
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