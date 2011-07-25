using System;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    public class Downstairs : GameplayObject
    {
        public Downstairs(int x, int y)
        {
            Initialize(x, y, SpriteType.DOWNSTAIRS, GameObjectType.DOWNSTAIRS);
        }
        private Location GetTargetLocation()
        {
            return Location.Depths;
        }
        public override void Update()
        {
            var player = GameplayObjectManager.GetTouchingPlayer(this);
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