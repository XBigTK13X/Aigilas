using System.Collections.Generic;
using System.Linq;
using OGUR.Sprites;
using OGUR.Strategies;
using OGUR.Management;
using OGUR.GameObjects;

namespace OGUR.Creatures
{
    internal class Player : ICreature
    {
        private void Setup(int x, int y, int playerIndex)
        {
            base.Setup(x, y, CreatureType.PLAYER, new Stats(100, 100, 10, 10, 10, 10, 35));
            m_playerIndex = playerIndex;
            m_strategy = new ControlledByPlayer();
        }

        public Player(int x, int y, int playerIndex)
        {
            Setup(x, y, playerIndex);
        }
        public override void Update()
        {
            base.Update();
            if(!Collision.HitTest.IsTouching(this,GameplayObjectManager.GetObject(GameObjectType.DOWNSTAIRS)) && !Collision.HitTest.IsTouching(this,GameplayObjectManager.GetObject(GameObjectType.UPSTAIRS)))
            {
                InputManager.Unlock(InputManager.Commands.Confirm,m_playerIndex);
            }
        }
    }
}