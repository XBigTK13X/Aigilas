using System;
using OGUR.Creatures;
using OGUR.Management;

namespace OGUR.Factory
{
    internal class CreatureFactory
    {
        private static int s_playerCount = 0;

        public static ICreature Create(CreatureType type, int x, int y)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return (Player) GameplayObjectManager.AddObject(new Player(x, y, s_playerCount++));
                default:
                    throw new Exception("An undefined GameObjectType case was passed into the GameplayObjectFactory.");
            }
        }

        public static void ResetPlayerCount()
        {
            s_playerCount = 0;
        }

        public static void IncreasePlayerCount()
        {
            s_playerCount ++;
        }
    }
}