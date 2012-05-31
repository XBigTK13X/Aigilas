using OGUR.Creatures;
using OGUR.Dungeons;
using SPX.Core;
using SPX.Entities;
using OGUR.Management;
using System;

namespace OGUR.Entities
{
    [Serializable()]
    public class Downstairs : Entity
    {
        public Downstairs(Point2 location)
        {
            Initialize(location, SpriteType.DOWNSTAIRS, OGUR.EntityType.DOWNSTAIRS,ZDepth.Stairs);
        }
        private int GetTargetLocation()
        {
            return Location.Depths;
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
                    DungeonFactory.GetNextFloor(GetTargetLocation());
                }
            }
        }
    }
}