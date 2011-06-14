using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Creatures;
using OGUR.Management;

namespace OGUR.Factory
{
    class CreatureFactory
    {
        static private int s_playerCount = 0;
        static public ICreature Create(CreatureType type, int x, int y)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return (Player)GameplayObjectManager.AddObject(new Player(x, y, s_playerCount++));
                default:
                    throw new Exception("An undefined GameObjectType case was passed into the GameplayObjectFactory.");
            }
        }
        static public void ResetPlayerCount()
        {
            s_playerCount = 0;
        }
        static public void IncreasePlayerCount()
        {
            s_playerCount ++;
        }
    }
}
