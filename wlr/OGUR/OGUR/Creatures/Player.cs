using System.Collections.Generic;
using OGUR.Sprites;
using OGUR.Strategies;

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
    }
}