using System.Collections.Generic;
using System.Linq;
using OGUR.Classes;
using OGUR.Sprites;
using OGUR.Strategies;
using OGUR.Management;
using OGUR.GameObjects;

namespace OGUR.Creatures
{
    public class Player : ICreature
    {
        private void Setup(int x, int y, int playerIndex)
        {
            m_playerIndex = playerIndex;
            m_strategy = new ControlledByPlayer(this);
            base.Setup(x, y, CreatureType.PLAYER, new Stats(100f, 100f, 10f, 10f, 10f, 10f, 35f,50f,6.0f),new Mage());
        }

        public Player(int x, int y, int playerIndex)
        {
            Setup(x, y, playerIndex);
        }
    }
}