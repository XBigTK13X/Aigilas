using System;
using OGUR.Collision;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    public class Downstairs : GameplayObject
    {
        public Downstairs(Point2 location)
        {
            Initialize(location, SpriteType.DOWNSTAIRS, GameObjectType.DOWNSTAIRS);
        }
        private Location GetTargetLocation()
        {
            return Location.Depths;
        }
        private ICreature player;
        public override void Update()
        {
            player = GameplayObjectManager.GetTouchingPlayer(this);
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