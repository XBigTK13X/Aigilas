using System.Collections.Generic;
using System.Linq;
using OGUR.Sprites;
using OGUR.Strategies;
using OGUR.Management;
using OGUR.GameObjects;

namespace OGUR.Creatures
{
    public class Player : ICreature
    {
        private bool m_isPlaying = false;

        private void Setup(int x, int y, int playerIndex)
        {
            m_playerIndex = playerIndex;
            m_strategy = new ControlledByPlayer();
            base.Setup(x, y, CreatureType.PLAYER, new Stats(100, 100, 10, 10, 10, 10, 35,50,6.0));
        }

        public Player(int x, int y, int playerIndex)
        {
            Setup(x, y, playerIndex);
        }
        public override void Update()
        {
            if (m_isPlaying)
            {
                base.Update();
            }
        }
    }
}