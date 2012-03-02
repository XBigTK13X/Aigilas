using OGUR.Creatures;
using OGUR.Dungeons;
using SPX.Entities;
using SPX.Sprites;
using SPX.Core;

namespace OGUR.Entities
{
    public class Upstairs : Entity
    {
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
            player = EntityManager.GetTouchingPlayer(this);
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