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

        public override void Update()
        {
            foreach (ICreature player in GameplayObjectManager.GetObjects(CreatureType.PLAYER))
            {
                if (Collision.HitTest.IsTouching(player, this) && InputManager.IsPressed(InputManager.Commands.Confirm,player.GetPlayerIndex()))
                {
                    InputManager.Lock(InputManager.Commands.Confirm, 0);
                    DungeonManager.GotoNext();
                }
            }
        }
    }
}