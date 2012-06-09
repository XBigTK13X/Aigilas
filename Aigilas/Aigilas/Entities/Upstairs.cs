using Agilas.Creatures;
using Agilas.Dungeons;
using SPX.Entities;
using Agilas.Management;
using SPX.Core;

namespace Agilas.Entities
{
    public class Upstairs : Entity
    {
        public Upstairs(Point2 location)
        {
            Initialize(location, SpriteType.UPSTAIRS, Agilas.EntityType.UPSTAIRS,ZDepth.Stairs);
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