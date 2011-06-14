using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Management;
using OGUR.Collision;
using OGUR.Sprites;
using OGUR.GameObjects;
using OGUR.Strategies;

namespace OGUR.Creatures
{
    class Player:ICreature
    {
        private void Setup(int x, int y,int playerIndex)
        {
            base.Setup(x, y,CreatureType.PLAYER,SpriteType.PLAYER_STAND);
            m_playerIndex = playerIndex;
            m_strategy = new ControlledByPlayer();
            InitStats(new List<decimal>(){100,100,10,10,10,10,35,SpriteInfo.Height,COOLDOWN});
        }

        public Player(int x, int y,int playerIndex)
        {
            Setup(x, y,playerIndex);
        }
    }
}
