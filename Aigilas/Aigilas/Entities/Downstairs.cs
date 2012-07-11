using Aigilas.Creatures;
using Aigilas.Dungeons;
using SPX.Core;
using SPX.Entities;
using Aigilas.Management;

namespace Aigilas.Entities
{
    public class Downstairs : Entity
    {
        public Downstairs(Point2 location)
        {
            Initialize(location, SpriteType.DOWNSTAIRS, Aigilas.EntityType.DOWNSTAIRS,ZDepth.Stairs);
        }

        private ICreature player;
        public override void Update()
        {
            player = EntityManager.GetTouchingCreature(this) as ICreature;
            if (player != null)
            {
                if (player.IsInteracting())
                {
                    player.PerformInteraction();
                    DungeonFactory.GetNextFloor();
                }
            }
        }
    }
}